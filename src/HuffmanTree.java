import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.HashMap;

public class HuffmanTree {
    public static int[] buildFrequencyArray(InputStream in) throws IOException {
        int[] frequencies = new int[IHuffConstants.ALPH_SIZE + 1]; // 257
        BitInputStream frequencyBits = new BitInputStream(in);
        int numBits = frequencyBits.readBits(IHuffConstants.BITS_PER_WORD);
        while (numBits != -1) {
            frequencies[numBits]++;
            numBits = frequencyBits.readBits(IHuffConstants.BITS_PER_WORD);
        }
        frequencyBits.close();
        return frequencies;
    }

    public static TreeNode buildHuffmanTree(int[] frequencies) {
        PriorityQueue314<TreeNode> treeQueue = initialfrequencyQueue(frequencies);
        // build tree
        while (treeQueue.size() > 1) { // can build subtrees
            TreeNode leftNode = treeQueue.dequeue();
            TreeNode rightNode = treeQueue.dequeue();
            int parentFrequency = leftNode.getFrequency() + rightNode.getFrequency();
            TreeNode parentNode = new TreeNode(leftNode, parentFrequency, rightNode);
            treeQueue.enqueue(parentNode);
        }
        TreeNode root = treeQueue.dequeue(); // reached the root node
        return root;
    }

    private static PriorityQueue314<TreeNode> initialfrequencyQueue(int[] frequencies) {
        PriorityQueue314<TreeNode> treeQueue = new PriorityQueue314<>();
        // enqueue all frequency nodes where node exists
        for (int frequency: frequencies) {
            if (frequency != -1) {
                TreeNode bitNode = new TreeNode(null, frequency, null);
                treeQueue.enqueue(bitNode);
            }
        }
        return treeQueue;
    }

    public static Map<Integer, String> createMap(TreeNode root) {
        if (root == null) {
            throw new IllegalArgumentException("Root cannot be null");
        }
        Map<Integer, String> huffMap = new HashMap<>();
        recurse(huffMap, root, "");
        return huffMap;
    }

    private static void recurse(Map<Integer, String> huffMap, TreeNode huffNode, String path) {
        if (huffNode.isLeaf()) {
            huffMap.put(huffNode.getValue(), path);
        }
        recurse(huffMap, huffNode.getLeft(), path + "0");
        recurse(huffMap, huffNode.getRight(), path + "1");
    }



}
