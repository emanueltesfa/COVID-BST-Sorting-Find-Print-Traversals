/**
 * This class is what defines the Binary Search Tree and sorts by name with
 * multiple methods and a nested Node class.
 *
 * @author <Emanuel Tesfa>
 * @version <11/15/2020>
 */

public class BinarySearchTree {

	public Node top20[] = new Node[20];
	public Node bottom20[] = new Node[20];
	private Node root;

	private class Node {
		String name;
		double CFR;
		Node leftChild;
		Node rightChild;

		/**
		 * This method is the constructor for the nested Node class with the parameter
		 * of name and CFR.
		 * 
		 * @param Name is passed from main as a string
		 * @param CFR  is passed from main once calculated as a double
		 * @return
		 */

		public Node(String name, double CFR) {
			this.name = name;
			this.CFR = CFR;
		}

		/**
		 * The method prints in a proper fashion.
		 *
		 * @param
		 * @return
		 */

		public void printNode() {
			System.out.printf("%-40s%,-30.6f\n", name, CFR);
		}
	}

	/**
	 * This method is the constructor for the BST class where the root of the tree
	 * is initialized to null.
	 *
	 * @param
	 * @return
	 */

	public BinarySearchTree() {
		root = null;
	}

	/**
	 * This method iterates through the nodes and uses the compareTo library to
	 * compare the names of each node until found. Afterwards the method will return
	 * the CFR if found and -1 if not found. Also the number of nodes reached before
	 * returning will be printed regardless if found or not. Taking in one parameter
	 * of the name.
	 * 
	 *
	 * @param String name used to be compared through tree
	 * 
	 * @return -1 if not found
	 * @return current.CFR if found
	 */

	public double find(String name) {
		Node current = root;
		int numNode = 0;

		while (current.name.compareTo(name) != 0) {
			numNode += 1;
			if (current.name.compareTo(name) > 0)
				current = current.leftChild;
			else
				current = current.rightChild;
			if (current == null) {
				System.out.println(name + " was not found");
				System.out.println(numNode + "   nodes visited \n");
				return -1.0;
			}
		}

		System.out.println(name + "is found with CFR " + current.CFR);
		System.out.println(numNode + "   nodes visited\n  ");
		return current.CFR;
	}

	/**
	 * This method takes in the name and CFR, loading both elements into the node
	 * but organizing by name and inserting them into the tree.
	 *
	 * @param String name
	 * @param Double cfr - Calculated in the while loop in main
	 * @return description of the return value
	 */

	public void insert(String name, double cfr) {
		Node newNode = new Node(name, cfr);
		newNode.name = name;
		newNode.CFR = cfr;
		if (root == null)
			root = newNode;
		else {
			Node current = root;
			Node parent;
			while (true) {
				parent = current;
				if (current.name.compareTo(name) > 0) {
					current = current.leftChild;
					if (current == null) {
						parent.leftChild = newNode;
						return;
					}
				} else {
					current = current.rightChild;
					if (current == null) {
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}

	/**
	 * This method is of type void and follows multiple if-else statements to
	 * iterate entirely through node and delete if found
	 *
	 * @param String CountryName is passed in order to be searched through but
	 *               deleted
	 * @return description of the return value
	 */

	public void delete(String countryName) {
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;
		while (current.name.compareTo(countryName) != 0) {
			parent = current;
			if (countryName.compareTo(current.name) < 0) {
				isLeftChild = true;
				current = current.leftChild;
			} else {
				isLeftChild = false;
				current = current.rightChild;
			}
			if (current == null)
				break;
		}
		if (current.leftChild == null && current.rightChild == null) {
			if (current == root)
				root = null;
			else if (isLeftChild)
				parent.leftChild = null;
			else // from parent
				parent.rightChild = null;
		}

		else if (current.rightChild == null)
			if (current == root)
				root = current.leftChild;
			else if (isLeftChild)
				parent.leftChild = current.leftChild;
			else
				parent.rightChild = current.leftChild;
		else if (current.leftChild == null)
			if (current == root)
				root = current.rightChild;
			else if (isLeftChild)
				parent.leftChild = current.rightChild;
			else
				parent.rightChild = current.rightChild;
		else {
			Node successor = getSuccessor(current);
			if (current == root)
				root = successor;
			else if (isLeftChild)
				parent.leftChild = successor;
			else
				parent.rightChild = successor;
			successor.leftChild = current.leftChild;
		}
		if (current != null)
			System.out.println(countryName + " was deleted");

	}

	/**
	 * This method finds the next generation of node once deleted inside delete
	 * method with the delNode is the node that the children are being searched for
	 * and returned when the next generation is found/
	 *
	 * @param String countryNmae
	 * @return successor
	 */

	public Node getSuccessor(Node delNode) {
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild;
		while (current != null) {
			successorParent = successor;
			successor = current;
			current = current.leftChild;
		}
		if (successor != delNode.rightChild) {
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}

	/**
	 * List is preOrder traversed with the localRoot being passed.
	 *
	 * @param Node LocalRoot
	 * @return
	 */

	public void preOrder(Node localRoot) {
		if (localRoot != null) {
			localRoot.printNode();
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
	}

	/**
	 * List is in-orderly traversed with the localRoot being passed.
	 *
	 * @param Node LocalRoot
	 * @return
	 */

	public void inOrder(Node localRoot) {
		if (localRoot != null) {
			inOrder(localRoot.leftChild);
			localRoot.printNode();
			inOrder(localRoot.rightChild);
		}
	}

	/**
	 * List is post orderly traversed with the localRoot being passed.
	 *
	 * @param Node LocalRoot
	 * @return
	 */

	public void postOrder(Node localRoot) {
		if (localRoot != null) {
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			localRoot.printNode();
		}
	}

	/**
	 * The inOrder() method is being recursively called until the while loop equals
	 * null and this method also formats the list.
	 *
	 * @param
	 * @return
	 */

	public void printInorder() {
		System.out.println("\nIn Order Traversal");
		printTable();
		inOrder(this.root);
		System.out.println("\n");
	}

	/**
	 * The postOrder() method is being recursively called until the while loop
	 * equals null and this method also formats the list.
	 *
	 * @param
	 * @return
	 */

	public void printPostorder() {
		System.out.println("\nPost Order Traversal");
		printTable();
		postOrder(this.root);
		System.out.println("\n");
	}

	/**
	 * The preOrder() method is being recursively called until the while loop equals
	 * null and this method also formats the list.
	 *
	 * @param
	 * @return
	 */

	public void printPreorder() {
		System.out.println("\nPre Order Traversal");
		printTable();
		preOrder(this.root);
		System.out.println("\n");
	}

	/**
	 * This method is the literal input and output to/from the Node type array. Both
	 * its name and CFR are printed. Once loaded, the for loop calls inOrderTop20()
	 * to organize.
	 * 
	 * @param
	 * @return
	 */

	public void printTop20() {

		Node tempNode2 = new Node(null, 0.0);
		int length = top20.length;

		for (int i = 0; i < length; i++) {
			top20[i] = tempNode2;
			inOrderTop20(root, tempNode2, top20, i);

		}

		System.out.println("\n\nTop twenty countries regarding CFR\n");
		printTable();

		for (int j = 0; j < 20; j++) {
			top20[j].printNode();
		}

	}

	/**
	 * This method handles the organization of the array while keeping track of max
	 * and the current Node (r). As a void type it returns nothing but the array and
	 * index are both still passed in.
	 *
	 * @param Node r
	 * @param Node max
	 * @param Node array a[]
	 * @param int  i
	 * @return description of the return value
	 */

	private void inOrderTop20(Node r, Node max, Node a[], int i) {
		if (r != null) {
			inOrderTop20(r.leftChild, max, a, i);
			if (max.CFR < r.CFR) {
				if (!(isInArrayTop20(a, r, i))) {
					max = r;

					if (max.CFR > a[i].CFR) {
						a[i] = max;
					}

				}
			}
			inOrderTop20(r.rightChild, max, a, i);
		}
	}

	/**
	 * This method is of boolean type to return true or false if the node is already
	 * in the array.
	 *
	 * @param Node a[]
	 * @param Node r
	 * @param int  length
	 * @return True - if found in array
	 * @return False - if not found in array
	 * 
	 */

	private boolean isInArrayTop20(Node a[], Node r, int length) {
		for (int i = 0; i < length; i++) {
			if (a[i] == r)
				return true;
		}
		return false;
	}

	/**
	 * This method is the literal input and output to/from the Node type array. Both
	 * its name and CFR are printed. Once loaded, the for loop calls
	 * inOrderBottom20() to organize.
	 * 
	 * @param
	 * @return
	 */
	public void printBottom20() {

		Node tempNode2 = new Node(null, 100);
		int length = bottom20.length;

		for (int i = 0; i < length; i++) {
			bottom20[i] = tempNode2;
			inOrderBottom20(root, tempNode2, bottom20, i);
		}

		System.out.println("\n\nBottom twenty countries regarding CFR\n");
		printTable();

		for (int j = 0; j < 20; j++) {
			bottom20[j].printNode();
		}

	}

	/**
	 * This method handles the organization of the array while keeping track of max
	 * and the current Node (r). As a void type it returns nothing but the array and
	 * index are both still passed in.
	 *
	 * @param Node r
	 * @param Node max
	 * @param Node array a[]
	 * @param int  i
	 * @return description of the return value
	 */

	private void inOrderBottom20(Node r, Node min, Node a[], int i) {
		if (r != null) {
			inOrderBottom20(r.leftChild, min, a, i);

			if (min.CFR > r.CFR) {
				if (!(isInArrayBottom20(a, r, i))) {
					min = r;

					if (min.CFR < a[i].CFR) {
						a[i] = min;
					}

				}
			}
			inOrderBottom20(r.rightChild, min, a, i);
		}
	}

	/**
	 * This method is of boolean type to return true or false if the node is already
	 * in the array.
	 *
	 * @param Node a[]
	 * @param Node r
	 * @param int  length
	 * @return True - if found in array
	 * @return False - if not found in array
	 * 
	 */
	private boolean isInArrayBottom20(Node a[], Node r, int length) {
		for (int i = 0; i < length; i++) {
			if (a[i] == r)
				return true;
		}
		return false;
	}

	/**
	 * This method directly prints the structured portion of the table for
	 * organizational purposes
	 * 
	 * @param
	 * @return
	 */

	public void printTable() {
		System.out.println(
				"Name   					CFR       \n" 
		+ "------------------------------------------------");
	}
}
