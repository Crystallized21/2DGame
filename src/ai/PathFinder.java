package ai;

import main.GamePanel;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PathFinder {
    final GamePanel gp;
    Node[][] node;
    final ArrayList<Node> openList = new ArrayList<>();
    public final ArrayList<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;

    public PathFinder(GamePanel gp) {
        this.gp = gp;
        instantiateNodes();
    }

    /**
     * Initializes and populates a 2D array of Node objects based on the maximum world column
     * and row dimensions defined in the associated GamePanel instance. Each Node is assigned
     * its respective column and row position.
     * <p>
     * The method iterates over the grid dimensions and creates a new Node object for each
     * position, ensuring the grid is fully populated. Node objects are created sequentially
     * column by column and row by row.
     */
    public void instantiateNodes() {
        node = new Node[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            node[col][row] = new Node(col, row);

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    /**
     * Resets the state of all nodes and the pathfinding-related lists and variables.
     * <p>
     * The method iterates through all the nodes in the grid defined by the maximum
     * world column and row dimensions of the associated `GamePanel` instance. For each node,
     * it resets the flags indicating whether the node is open, checked, or solid. Subsequently,
     * it clears the `openList` and `pathList`, resets the goal-related flag, and re-initializes
     * the step counter to zero.
     */
    public void resetNodes() {
        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            // Reset open, checked, and solid state
            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }

        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    /**
     * Sets the start and goal nodes for the pathfinding algorithm and initializes
     * the grid with necessary properties such as collision and cost values.
     * <p>
     * The method first resets all nodes and pathfinding-related lists and variables.
     * Then, it defines the start node, goal node, and current node, and adds the
     * current node to the open list. It iterates through the grid to configure
     * collision properties based on the map data and calculates the cost for
     * each node in the grid.
     *
     * @param startCol The column index of the starting node.
     * @param startRow The row index of the starting node.
     * @param goalCol The column index of the goal node.
     * @param goalRow The row index of the goal node.
     */
    public void setNodes(int startCol, int startRow, int goalCol, int goalRow) {
        resetNodes();

        // Set start and goal nodes
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);

        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            // Set Solid Nodes, so check tiles
            int tileNum = gp.tileM.mapTileNum[gp.currentMap][col][row];
            if (gp.tileM.tile[tileNum].collision) {
                node[col][row].solid = true;
            }

            // Check interactive tiles
            for (int i = 0; i < gp.iTile[1].length; i++) {
                if (gp.iTile[gp.currentMap][i] != null && gp.iTile[gp.currentMap][i].destructible) {
                    int itCol = gp.iTile[gp.currentMap][i].worldX / gp.tileSize;
                    int itRow = gp.iTile[gp.currentMap][i].worldY / gp.tileSize;
                    node[itCol][itRow].solid = true;
                }
            }

            // Set Cost
            getCost(node[col][row]);

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    /**
     * Calculates the cost values (G, H, and F) for the given node in the context of pathfinding.
     * The G cost represents the movement cost from the start node to the current node.
     * The H cost represents the estimated movement cost from the current node to the goal node
     * using the Manhattan distance heuristic. The F cost is the total cost (G + H).
     *
     * @param node The node for which the cost values are to be calculated.
     */
    public void getCost(Node node) {
        // G Cost
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;

        // H Cost
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;

        // F Cost
        node.fCost = node.gCost + node.hCost;
    }

    /**
     * Executes a pathfinding algorithm to reach the goal node from the current node.
     * The method iteratively evaluates and expands neighboring nodes to find the shortest path
     * based on cost values. It continues until the goal is reached, the open list is empty,
     * or a predefined step limit is exceeded.
     * <p>
     * During execution, the method:
     * - Marks the current node as checked.
     * - Adds valid neighboring nodes to the open list.
     * - Determines the next node to process based on the lowest F cost (or G cost if F costs are equal).
     * - Tracks the path if the goal node is reached.
     *
     * @return true if the goal node is successfully reached, false otherwise.
     */
    public boolean search() {
        while (!goalReached && step < 500) {
            int col = currentNode.col;
            int row = currentNode.row;

            // Check current node
            currentNode.checked = true;
            openList.remove(currentNode);

            // Open Up node
            if (row - 1 >= 0) {
                openNode(node[col][row - 1]);
            }
            // Open Left node
            if (col - 1 >= 0) {
                openNode(node[col - 1][row]);
            }
            // Open Down node
            if (row + 1 < gp.maxWorldRow) {
                openNode(node[col][row + 1]);
            }
            // Open Right node
            if (col + 1 < gp.maxWorldCol) {
                openNode(node[col + 1][row]);
            }

            // Find the node with the lowest F Cost
            int bestNodeIndex = 0;
            int bestNodeFCost = 999;

            for (int i = 0; i < openList.size(); i++) {
                // Check if this node's F cost is better
                if (openList.get(i).fCost < bestNodeFCost) {
                    bestNodeIndex = i;
                    bestNodeFCost = openList.get(i).fCost;
                }
                // If F cost is equal, check the G cost
                else if (openList.get(i).fCost == bestNodeFCost) {
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }

            // If there is no node with the lowest F cost, break the loop
            if (openList.isEmpty()) {
                break;
            }

            // After the loop, openList will be the next step
            currentNode = openList.get(bestNodeIndex);

            if (currentNode == goalNode) {
                goalReached = true;
                trackPath();
            }
            step++;
        }

        return goalReached;
    }

    /**
     * Adds a node to the open list if it is not already open, checked, or marked as solid.
     * The method also sets the current node as the parent of the node being opened.
     *
     * @param node The node to be added to the open list. Must not be null.
     */
    public void openNode(@NotNull Node node) {
        if (!node.open && !node.checked && !node.solid) {
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }

    /**
     * Tracks the path from the goal node back to the start node by iteratively following
     * the parent references of each node. The discovered path is added to the `pathList`
     * in reverse order, starting from the goal node and ending at the start node.
     * <p>
     * The method assumes that the nodes' parent references have already been set by
     * the pathfinding process and that a valid path exists from the start node to the
     * goal node. The `startNode` and `goalNode` must be defined beforehand.
     * <p>
     * This method is typically called after the pathfinding algorithm has successfully
     * reached the goal node.
     */
    public void trackPath() {
        Node current = goalNode;

        while (current != startNode) {
            pathList.addFirst(current);
            current = current.parent;
        }
    }
}
