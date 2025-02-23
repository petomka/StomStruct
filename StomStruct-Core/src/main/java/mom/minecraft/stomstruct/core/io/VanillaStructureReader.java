package mom.minecraft.stomstruct.core.io;

import mom.minecraft.stomstruct.core.structure.Structure;
import net.minestom.server.instance.block.Block;
import org.jglrxavpok.hephaistos.nbt.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Reads NBT structure files compatible with vanilla structure blocks.
 */
public class VanillaStructureReader implements IStructureReader {

    @Override
    public Structure read(String path) throws StructureFormatException, IOException {

        int blockCount;
        short[][] blockPositions;
        short[] blockStates;
        NBTCompound[] blockNBT;

        int paletteCount;
        Block[][] blockStatePalettes;

        int width; // X
        int height; // Y
        int length; // Z

        // Check that the schematic file is indeed a real file
        File schematicFile = new File(path);
        if (!schematicFile.exists() || !schematicFile.isFile()) {
            throw new FileNotFoundException("Structure file not found");
        }

        // Create NBT Reader and process the supplied structure file
        // TODO is GZIP the always right choice? Determine type of compression?
        try (NBTReader reader = new NBTReader(schematicFile, CompressedProcesser.GZIP)) {

            // Read in all NBT from the structure file
            NBTCompound structureNBT = (NBTCompound) reader.read();

            NBTList<NBTInt> structureSize = structureNBT.getList("size");
            NBTList<NBTCompound> structureBlocks = structureNBT.getList("blocks");
            NBTList<NBTCompound> structureEntities = structureNBT.getList("entities");

            // There can be one "palette" or multiple under "palettes"
            NBTList<NBTList<?>> structurePalettes;
            if (structureNBT.containsKey("palettes")) {
                structurePalettes = structureNBT.getList("palettes");
            } else if (structureNBT.containsKey("palette")) {
                var palette = structureNBT.getList("palette");
                structurePalettes = NBT.List(NBTType.TAG_List, palette);
            } else {
                throw new StructureFormatException("Structure has no palette(s)");
            }

            blockCount = structureBlocks.getSize();
            paletteCount = structurePalettes.getSize();

            width = structureSize.get(0).getValue();
            height = structureSize.get(1).getValue();
            length = structureSize.get(2).getValue();

            // Initialize arrays for structure data
            blockPositions = new short[blockCount][3];
            blockStates = new short[blockCount];
            blockStatePalettes = new Block[paletteCount][];
            blockNBT = new NBTCompound[blockCount];

            // Load in all structure block data
            for (short i = 0; i < blockCount; i++) {
                NBTCompound structureBlock = structureBlocks.get(i);
                NBTList<NBTInt> pos = structureBlock.getList("pos");
                blockPositions[i] = new short[3];
                for (int j = 0; j < 3; j++) {
                    blockPositions[i][j] = (short) pos.get(j).getValue();
                }
                blockStates[i] = structureBlock.getAsShort("state");

                if (structureBlock.containsKey("nbt")) {
                    blockNBT[i] = structureBlock.getCompound("nbt");
                }
            }

            // Load in all palette block data
            Map<String, String> tempPropsMap = new HashMap<>();
            for (int i = 0; i < paletteCount; i++) {
                NBTList<?> nbtPalette = structurePalettes.get(i);
                blockStatePalettes[i] = new Block[nbtPalette.getSize()];

                for (int j = 0; j < nbtPalette.getSize(); j++) {
                    NBTCompound swatch = (NBTCompound) nbtPalette.get(j);

                    if (swatch.containsKey("Properties")) {
                        NBTCompound props = swatch.getCompound("Properties");

                        if (!tempPropsMap.isEmpty()) {tempPropsMap.clear();}

                        for (String key : props.getKeys()) {
                            tempPropsMap.put(key, props.getString(key));
                        }

                        blockStatePalettes[i][j] = Block.fromNamespaceId(swatch.getString("Name")).withProperties(tempPropsMap);
                    } else {
                        blockStatePalettes[i][j] = Block.fromNamespaceId(swatch.getString("Name"));
                    }
                }
            }
        } catch (NullPointerException | NBTException ex) {
            throw new StructureFormatException(ex.getMessage());
        }

        return new Structure(blockPositions, blockStates, blockNBT, blockStatePalettes, width, height, length);
    }
}
