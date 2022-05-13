package mom.minecraft.stomstruct.core.io;

import mom.minecraft.stomstruct.core.structure.Structure;
import net.minestom.server.instance.block.Block;
import org.jglrxavpok.hephaistos.nbt.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Writes NBT structure files compatible with vanilla structure blocks.
 */
public class VanillaStructureWriter implements IStructureWriter {

    @Override
    public void write(Structure structure, String path) throws IOException {

        List<NBTCompound> structureBlocks = new ArrayList<>();
        List<NBTCompound> structureEntities = new ArrayList<>();
        List<List<NBTCompound>> structurePalettes = new ArrayList<>();
        List<NBTInt> structureSize = new ArrayList<>();
        NBTInt structureDataVersion = new NBTInt(-1);

        // Populate blocks list
        for (int i = 0; i < structure.getBlockCount(); i++) {
            // Add block state
            NBTInt blockState = new NBTInt(structure.getBlockStates()[i]);

            // Add block positions
            NBTList<NBTInt> blockPos = NBT.List(NBTType.TAG_Int, List.of(
                    new NBTInt(structure.getBlockPositions()[i][0]),
                    new NBTInt(structure.getBlockPositions()[i][1]),
                    new NBTInt(structure.getBlockPositions()[i][2])
            ));

            final int index = i;

            NBTCompound block = NBT.Compound(t -> {
                t.set("pos", blockPos);
                t.set("state", blockState);

                // Optionally add block NBT
                if (structure.getBlockNBT()[index] != null) {
                    NBTCompound nbt = structure.getBlockNBT()[index];
                    t.set("nbt", nbt);
                }
            });

            structureBlocks.add(block);
        }

        // Populate palette(s)
        for (int i = 0; i < structure.getPaletteCount(); i++) {
            List<NBTCompound> palette = new ArrayList<>();

            for (int j = 0; j < structure.getBlockStatePalettes()[i].length; j++) {
                final int j_ = j;
                NBTCompound swatch = NBT.Compound(t -> {
                    Block block = structure.getBlockStatePalettes()[0][j_];
                    t.set("Name", new NBTString(block.name()));

                    if (!block.properties().isEmpty()) {
                        NBTCompound swatchProperties = NBT.Compound(tp -> {
                            for (String key : block.properties().keySet()) {
                                tp.set(key, new NBTString(block.getProperty(key)));
                            }
                        });

                        t.set("Properties", swatchProperties);
                    }
                });

                palette.add(swatch);
            }
            structurePalettes.add(palette);
        }

        // Populate size
        structureSize.add(new NBTInt(structure.getWidth()));
        structureSize.add(new NBTInt(structure.getHeight()));
        structureSize.add(new NBTInt(structure.getLength()));

        // Create final NBT Compound for structure
        NBTCompound structureNBT = NBT.Compound(t -> {
            t.set("blocks", NBT.List(NBTType.TAG_Compound, structureBlocks));
            t.set("entities", NBT.List(NBTType.TAG_Compound, structureEntities));
            if (structure.getPaletteCount() == 1) {
                t.set("palette", NBT.List(NBTType.TAG_Int, structurePalettes.get(0)));
            } else {
                t.set("palettes", NBT.List(NBTType.TAG_List,
                        structurePalettes
                                .stream()
                                .map(palette -> NBT.List(NBTType.TAG_Compound, palette))
                                .collect(Collectors.toList())));
            }
            t.set("size", NBT.List(NBTType.TAG_Int, structureSize));
            t.set("DataVersion", structureDataVersion);
        });


        // Write NBT to file
        try (NBTWriter writer = new NBTWriter(new File(path), CompressedProcesser.GZIP)) {
            writer.writeNamed("", structureNBT);
        }
    }
}
