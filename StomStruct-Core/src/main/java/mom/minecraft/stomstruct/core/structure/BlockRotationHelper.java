package mom.minecraft.stomstruct.core.structure;

import net.kyori.adventure.key.Key;
import net.minestom.server.instance.block.Block;

import java.util.HashMap;
import java.util.Map;

public class BlockRotationHelper {

    private static final Map<Key, RotationHandler> handlers = new HashMap<>();

    static {
        addHandler(Block.ANVIL, cardinalRotationHandler("facing"));
        addHandler(Block.SMALL_AMETHYST_BUD, cardinalRotationHandler("facing"));
        addHandler(Block.MEDIUM_AMETHYST_BUD, cardinalRotationHandler("facing"));
        addHandler(Block.LARGE_AMETHYST_BUD, cardinalRotationHandler("facing"));
        addHandler(Block.AMETHYST_CLUSTER, cardinalRotationHandler("facing"));
        Block.values().stream()
                .filter(block -> block.key().value().contains("banner"))
                .forEach(block -> {
                    if (block.key().value().contains("wall")) {
                        addHandler(block, cardinalRotationHandler("facing"));
                    } else {
                        addHandler(block, sixteenDirectionalRotationHandler());
                    }
                });
        addHandler(Block.BARREL, cardinalRotationHandler("facing"));
        addHandler(Block.BASALT, axialRotationHandler("axis"));
        addHandler(Block.POLISHED_BASALT, axialRotationHandler("axis"));
        Block.values().stream()
                .filter(block -> block.key().value().contains("bed"))
                .forEach(block -> addHandler(block, cardinalRotationHandler("facing")));
        addHandler(Block.BEEHIVE, cardinalRotationHandler("facing"));
        addHandler(Block.BELL, cardinalRotationHandler("facing"));
        addHandler(Block.BIG_DRIPLEAF, cardinalRotationHandler("facing"));
        addHandler(Block.BLAST_FURNACE, cardinalRotationHandler("facing"));
        addHandler(Block.BONE_BLOCK, axialRotationHandler("axis"));
        Block.values().stream()
                .filter(block -> block.key().value().contains("button"))
                .forEach(block -> addHandler(block, cardinalRotationHandler("facing")));
        addHandler(Block.CAMPFIRE, cardinalRotationHandler("facing"));
        addHandler(Block.SOUL_CAMPFIRE, cardinalRotationHandler("facing"));
        addHandler(Block.CHAIN, axialRotationHandler("axis"));
        addHandler(Block.CHEST, cardinalRotationHandler("facing"));
        addHandler(Block.TRAPPED_CHEST, cardinalRotationHandler("facing"));
        addHandler(Block.ENDER_CHEST, cardinalRotationHandler("facing"));
        addHandler(Block.COCOA, cardinalRotationHandler("facing"));
        Block.values().stream()
                .filter(block -> block.key().value().contains("command_block"))
                .forEach(block -> addHandler(block, cardinalRotationHandler("facing")));
        Block.values().stream()
                .filter(block -> block.key().value().contains("wall_fan"))
                .forEach(block -> addHandler(block, cardinalRotationHandler("facing")));
        addHandler(Block.DEEPSLATE, axialRotationHandler("axis"));
        addHandler(Block.DISPENSER, cardinalRotationHandler("facing"));
        addHandler(Block.DROPPER, cardinalRotationHandler("facing"));
        Block.values().stream()
                .filter(block -> block.key().value().contains("door"))
                .forEach(block -> addHandler(block, cardinalRotationHandler("facing")));
        addHandler(Block.END_PORTAL_FRAME, cardinalRotationHandler("facing"));
        addHandler(Block.END_ROD, cardinalRotationHandler("facing"));
        Block.values().stream()
                .filter(block -> block.key().value().contains("fence"))
                .forEach(block -> {
                    if (block.key().value().contains("gate")) {
                        addHandler(block, cardinalRotationHandler("facing"));
                    } else {
                        addHandler(block, multiFaceRotationHandler());
                    }
                });
        addHandler(Block.FURNACE, cardinalRotationHandler("facing"));
        Block.values().stream()
                .filter(block -> block.key().value().contains("glazed_terracotta"))
                .forEach(block -> addHandler(block, cardinalRotationHandler("facing")));
        addHandler(Block.GLOW_LICHEN, multiFaceRotationHandler());
        addHandler(Block.GRINDSTONE, cardinalRotationHandler("facing"));
        addHandler(Block.HAY_BLOCK, axialRotationHandler("axis"));
        addHandler(Block.HOPPER, cardinalRotationHandler("facing"));
        addHandler(Block.JACK_O_LANTERN, cardinalRotationHandler("facing"));
        addHandler(Block.LADDER, cardinalRotationHandler("facing"));
        addHandler(Block.LECTERN, cardinalRotationHandler("facing"));
        addHandler(Block.LEVER, cardinalRotationHandler("facing"));
        addHandler(Block.LIGHTNING_ROD, cardinalRotationHandler("facing"));
        Block.values().stream()
                .filter(block -> block.key().value().contains("log"))
                .forEach(block -> addHandler(block, axialRotationHandler("axis")));
        addHandler(Block.LOOM, cardinalRotationHandler("facing"));
        Block.values().stream()
                .filter(block -> block.key().value().contains("head"))
                .forEach(block -> {
                    if (block.key().value().contains("wall")) {
                        addHandler(block, cardinalRotationHandler("facing"));
                    } else {
                        addHandler(block, sixteenDirectionalRotationHandler());
                    }
                });
        addHandler(Block.MUSHROOM_STEM, multiFaceRotationHandler());
        addHandler(Block.NETHER_PORTAL, axialRotationHandler("axis"));
        addHandler(Block.RED_MUSHROOM_BLOCK, multiFaceRotationHandler());
        addHandler(Block.BROWN_MUSHROOM_BLOCK, multiFaceRotationHandler());
        addHandler(Block.OBSERVER, cardinalRotationHandler("facing"));
        addHandler(Block.PISTON, cardinalRotationHandler("facing"));
        addHandler(Block.PURPUR_PILLAR, axialRotationHandler("axis"));
        addHandler(Block.QUARTZ_PILLAR, axialRotationHandler("axis"));
        addHandler(Block.STICKY_PISTON, cardinalRotationHandler("facing"));
        addHandler(Block.PISTON_HEAD, cardinalRotationHandler("facing"));
        addHandler(Block.MOVING_PISTON, cardinalRotationHandler("facing"));
        addHandler(Block.PUMPKIN, cardinalRotationHandler("facing"));
        addHandler(Block.CARVED_PUMPKIN, cardinalRotationHandler("facing"));
        addHandler(Block.PUMPKIN_STEM, cardinalRotationHandler("facing"));
        addHandler(Block.COMPARATOR, cardinalRotationHandler("facing"));
        addHandler(Block.REPEATER, cardinalRotationHandler("facing"));
        addHandler(Block.REDSTONE_WALL_TORCH, cardinalRotationHandler("facing"));
        Block.values().stream()
                .filter(block -> block.key().value().contains("shulker_box"))
                .forEach(block -> addHandler(block, cardinalRotationHandler("facing")));
        Block.values().stream()
                .filter(block -> block.key().value().contains("sign"))
                .forEach(block -> {
                    if (block.key().value().contains("wall")) {
                        addHandler(block, cardinalRotationHandler("facing"));
                    } else {
                        addHandler(block, sixteenDirectionalRotationHandler());
                    }
                });
        addHandler(Block.SMALL_DRIPLEAF, cardinalRotationHandler("facing"));
        addHandler(Block.SMOKER, cardinalRotationHandler("facing"));
        Block.values().stream()
                .filter(block -> block.key().value().contains("stairs"))
                .forEach(block -> addHandler(block, cardinalRotationHandler("facing")));
        addHandler(Block.STONECUTTER, cardinalRotationHandler("facing"));
        addHandler(Block.WALL_TORCH, cardinalRotationHandler("facing"));
        addHandler(Block.SOUL_WALL_TORCH, cardinalRotationHandler("facing"));
        Block.values().stream()
                .filter(block -> block.key().value().contains("trapdoor"))
                .forEach(block -> addHandler(block, cardinalRotationHandler("facing")));
        addHandler(Block.TRIPWIRE_HOOK, cardinalRotationHandler("facing"));
        addHandler(Block.VINE, multiFaceRotationHandler());
        Block.values().stream()
                .filter(block -> block.key().value().endsWith("wall"))
                .forEach(block -> addHandler(block, multiFaceRotationHandler()));
        Block.values().stream()
                .filter(block -> block.key().value().contains("wood"))
                .forEach(block -> addHandler(block, axialRotationHandler("axis")));
    }

    private static void addHandler(Block block, RotationHandler handler) {
        handlers.put(block.key(), handler);
    }

    private static String rotateCardinalDirection(Rotation rotation, String cardinal) {
        int cardinalId = switch (cardinal) {
            case "north" -> 0;
            case "east" -> 1;
            case "south" -> 2;
            case "west" -> 3;
            default -> -1;
        };
        if (cardinalId == -1) {
            return cardinal; // Block is not facing in a cardinal direction but something else, e.g. "up" or "down"
        }
        cardinalId += rotation.ordinal();
        cardinalId %= 4;
        return switch (cardinalId) {
            case 1 -> "east";
            case 2 -> "south";
            case 3 -> "west";
            default -> "north";
        };
    }

    private static String rotateAxis(Rotation rotation, String axis) {
        int axisId = switch (axis) {
            case "x" -> 0;
            case "z" -> 1;
            default -> -1;
        };
        if (axisId == -1) {
            return axis;
        }
        axisId += rotation.ordinal();
        axisId %= 2;
        if (axisId == 0) {
            return "x";
        }
        return "z";
    }

    private static RotationHandler cardinalRotationHandler(String propertyName) {
        return (block, rotation) -> {
            var facing = block.getProperty(propertyName);
            facing = rotateCardinalDirection(rotation, facing);
            return block.withProperty(propertyName, facing);
        };
    }

    private static RotationHandler axialRotationHandler(String propertyName) {
        return (block, rotation) -> {
            var axis = block.getProperty(propertyName);
            axis = rotateAxis(rotation, axis);
            return block.withProperty(propertyName, axis);
        };
    }

    private static RotationHandler sixteenDirectionalRotationHandler() {
        return (block, rotation) -> {
            var bannerRotation = Integer.parseInt(block.getProperty("rotation"));
            bannerRotation += rotation.ordinal() * 4;
            bannerRotation %= 16;
            return block.withProperty("rotation", String.valueOf(bannerRotation));
        };
    }

    private static RotationHandler multiFaceRotationHandler() {
        int[][] rotationIndices = {
                {0, 1, 2, 3},
                {3, 0, 1, 2},
                {2, 3, 0, 1},
                {1, 2, 3, 0}
        };
        return (block, rotation) -> {
            var north = block.getProperty("north");
            var east = block.getProperty("east");
            var south = block.getProperty("south");
            var west = block.getProperty("west");

            String[] rotated = {north, east, south, west};
            int[] indices = rotationIndices[rotation.ordinal()];
            rotated = new String[]{rotated[indices[0]], rotated[indices[1]], rotated[indices[2]], rotated[indices[3]]};

            return block.withProperty("north", String.valueOf(rotated[0]))
                    .withProperty("east", String.valueOf(rotated[1]))
                    .withProperty("south", String.valueOf(rotated[2]))
                    .withProperty("west", String.valueOf(rotated[3]));
        };
    }

    public static Block rotateBlock(Block block, Rotation rotation) {
        var handler = handlers.get(block.key());
        if (handler == null) {
            return block;
        }
        return handler.rotate(block, rotation);
    }

    private interface RotationHandler {
        Block rotate(Block block, Rotation rotation);
    }

}
