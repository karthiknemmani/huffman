import java.io.IOException;
import java.io.InputStream;

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
        // set initial subtree and enqueue weight node
        TreeNode parentNode = setInitialSubtree(treeQueue);
        treeQueue.enqueue(parentNode);
        
    }

    private static TreeNode setInitialSubtree(PriorityQueue314<TreeNode> treeQueue) {
        TreeNode leftNode = treeQueue.dequeue();
        TreeNode rightNode = treeQueue.dequeue();
        int parentFrequency = leftNode.getFrequency() + rightNode.getFrequency();
        TreeNode parentNode = new TreeNode(leftNode, parentFrequency, rightNode);
        return parentNode;
    }

    private static PriorityQueue314<TreeNode> initialfrequencyQueue(int[] frequencies) {
        PriorityQueue314<TreeNode> treeQueue = new PriorityQueue314<>();
        // enqueue all frequency nodes
        for (int frequency: frequencies) {
            if (frequency != -1) {
                TreeNode bitNode = new TreeNode(null, frequency, null);
            treeQueue.enqueue(bitNode);
            }
            }
            
        }
        return treeQueue;
    }
}
