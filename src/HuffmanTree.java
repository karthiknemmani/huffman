import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.HashMap;

public class HuffmanTree {
    public int[] buildFrequencyArray(InputStream in) throws IOException {
        int[] frequencies = new int[IHuffConstants.ALPH_SIZE + 1]; // 257
        BitInputStream bitInput = new BitInputStream(in);
        int numBits = bitInput.readBits(IHuffConstants.BITS_PER_WORD);
        while (numBits != -1) {
            frequencies[numBits]++;
            numBits = bitInput.readBits(IHuffConstants.BITS_PER_WORD);
        }
        bitInput.close();
        return frequencies;
    }

    public TreeNode buildHuffmanTree(int[] frequencies) {
        PriorityQueue314<TreeNode> treeQueue = initialfrequencyQueue(frequencies);
        if (treeQueue.isEmpty()) {
            return null;
        }
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

    private PriorityQueue314<TreeNode> initialfrequencyQueue(int[] frequencies) {
        PriorityQueue314<TreeNode> treeQueue = new PriorityQueue314<>();
        // enqueue all frequency nodes where node exists
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] != 0) {
                TreeNode bitNode = new TreeNode(null, i, null);
                treeQueue.enqueue(bitNode);
            }
        }
        return treeQueue;
    }

    public Map<Integer, String> createMap(TreeNode root) {
        if (root == null) {
            throw new IllegalArgumentException("Root cannot be null");
        }
        Map<Integer, String> huffMap = new HashMap<>();
        recurse(huffMap, root, "");
        return huffMap;
    }

    private void recurse(Map<Integer, String> huffMap, TreeNode huffNode, String path) {
        if (huffNode.isLeaf()) {
            huffMap.put(huffNode.getValue(), path);
        }
        recurse(huffMap, huffNode.getLeft(), path + "0");
        recurse(huffMap, huffNode.getRight(), path + "1");
    }



}
