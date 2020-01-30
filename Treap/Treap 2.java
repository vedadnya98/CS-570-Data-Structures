package firstclass;

import java.util.*;

public class Treap<E extends Comparable<E>> {

	private static class Node<E> {
		public E data;
		public int priority;
		public Node<E> left;
		public Node<E> right;

		public Node(E data, int priority) {
			this.data = data;
			this.priority = priority;
			left = null;
			right = null;
		}

		Node<E> rotateRight() {
			Node<E> temp = this.left;
			Node<E> R = temp.right;
			temp.right = this;
			this.left = R;
			return temp;
		}

		Node<E> rotateLeft() {
			Node<E> temp = this.right;
			Node<E> L = temp.left;
			temp.left = this;
			this.right = L;
			return temp;
		}

		public String toString() {
			return data.toString();
		}
	}

	private Random priorityGenerator;
	private Node<E> root;

	public Treap() {
		priorityGenerator = new Random();
		root = null;
	}

	public Treap(long seed) {
		priorityGenerator = new Random(seed);
		root = null;
	}

	boolean add(E key) {
		int priority = priorityGenerator.nextInt();
		return add(key, priority);

	}
/**
 * To add elements in tree
 * @param key
 * @param priority
 * @return
 */
	boolean add(E key, int priority) {
		Node<E> newData = new Node<E>(key, priority);
		Stack<Node<E>> s = new Stack<Node<E>>();

		if (root == null) {
			root = newData;
			return true;
		}
		Node<E> current = root;
		Node<E> parent = null;
		while (current != null) {
			parent = current;
			s.push(parent);
			if (key.compareTo(current.data) < 0) {
				current = current.left;
				if (current == null) {
					parent.left = newData;
					break;
				}

			} else if (key.compareTo(current.data) > 0) {
				current = current.right;
				if (current == null) {
					parent.right = newData;
					break;
				}
			} else if (key.compareTo(current.data) == 0) {
				return false;
			}
		}

		reheap(s, newData);
		return true;
	}

	boolean reheap(Stack<Node<E>> s, Node<E> newData) {
		Node<E> temp = null;
		while (newData.priority > s.peek().priority) {
			temp = s.pop();
			if (!s.isEmpty()) {
				if (newData.data.compareTo(temp.data) < 0) {
					temp = temp.rotateRight();

					if (temp.data.compareTo(s.peek().data) < 0) {
						s.peek().left = temp;
					} else if (temp.data.compareTo(s.peek().data) > 0) {
						s.peek().right = temp;
					}

				} else if (newData.data.compareTo(temp.data) > 0) {
					temp = temp.rotateLeft();

					if (temp.data.compareTo(s.peek().data) < 0) {
						s.peek().left = temp;
					} else if (temp.data.compareTo(s.peek().data) > 0) {
						s.peek().right = temp;
					}
				}
			} else {
				if (newData.equals(temp.right)) {
					root = temp.rotateLeft();
					return true;
				} else if (newData.equals(temp.left)) {
					root = temp.rotateRight();
					return true;
				}
			}

		}
		return true;
	}

	/**
	 * To find elements in the tree
	 * @param root
	 * @param key
	 * @return
	 */
	
	private boolean find(Node<E> root, E key) {
		Node<E> r = root;
		while (true) {
			if (r != null) {
				if (key.compareTo(r.data) == 0) {
					return true;
				} else if (key.compareTo(r.data) < 0) {
					r = r.left;
				} else if (key.compareTo(r.data) > 0) {
					r = r.right;
				}
			} else {
				return false;
			}
		}
	}

	public boolean find(E key) {
		Node<E> r = root;
		while (true) {
			if (r != null) {
				if (key.compareTo(r.data) == 0) {
					return true;
				} else if (key.compareTo(r.data) < 0) {
					r = r.left;
				} else if (key.compareTo(r.data) > 0) {
					r = r.right;
				}
			} else {
				return false;
			}
		}
	}

	/**
	 * To delete elements from the tree
	 * @param key
	 * @return
	 */
	
	boolean delete(E key) {

		if (find(key) == false) {
			return false;
		}
		Node<E> r = root;
		Node<E> target = null;
		Node<E> parent = root;
		Node<E> new_head = null;
		Node<E> new_parent = null;
		if (root == null) {
			return false;
		} else if (key == r.data && r.right == null && r.left == null) {
			root = null;
			return true;
		}
		while (true) {
			if (key.compareTo(r.data) < 0) {
				parent = r;
				r = r.left;
			} else if (key.compareTo(r.data) > 0) {
				parent = r;
				r = r.right;
			} else if (key.compareTo(r.data) == 0) {
				target = r;
				break;
			}
		}
		if (target.right == null && target.left == null) {
			if (parent.left == target) {
				parent.left = null;
			} else {
				parent.right = null;
			}
			return true;
		}
		while (target.right != null || target.left != null) {
			if (target.right != null && target.left != null) {
				if (target.right.priority > target.left.priority) {
					new_head = target.rotateLeft();
					target = new_head.left;
				} else {
					new_head = target.rotateRight();
					target = new_head.right;
				}
			} else if (target.right == null && target.left != null) {
				new_head = target.rotateRight();
				target = new_head.right;
			} else if (target.right != null && target.left == null) {
				new_head = target.rotateLeft();
				target = new_head.left;
			}
			if(target.data == parent.data) {
				root = new_head;
			}
			else if (parent.left == target) {
				parent.left = new_head;
			} else {
				parent.right = new_head;
			}
			parent = new_head;
			new_parent = new_head;
		}
		if (new_parent.left == target) {
			new_parent.left = null;
		} else {
			new_parent.right = null;
		}
		return true;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		preOrderTraverse(root, 1, sb);
		return sb.toString();
	}

	private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
		for (int i = 1; i < depth; i++) {
			sb.append(" ");
		}
		if (node == null) {
			sb.append("null\n");
		} else {
			sb.append("(key=");
			sb.append(node.toString());
			sb.append(", ");
			sb.append("priority=");
			sb.append(node.priority);
			sb.append(")");
			sb.append("\n");
			preOrderTraverse(node.left, depth + 1, sb);
			preOrderTraverse(node.right, depth + 1, sb);
		}
	}

	public static void main(String[] args) {
		Treap<Integer> tree = new Treap<Integer>();

		
		/**
		 * adding elements in the tree
		 */
		tree.add(4, 19);
		tree.add(2, 31);
		tree.add(6, 70);
		tree.add(1, 84);
		tree.add(3, 12);
		tree.add(5, 83);
		tree.add(7, 26);

		/**
		 * Finding an element
		 */
		
		System.out.println("Is the node found?: " + tree.find(4));

		/**
		 * String representation of tree
		 */
		
		System.out.println("The string representation is :\n" + tree.toString());

		System.out.println("Is the node found?: " +tree.delete(5));

		System.out.println("The string representation is :\n" + tree.toString());
	}
}
