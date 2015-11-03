import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BST<T extends Comparable<T>>{
	
	Node<T> tree = null;
	
	public BST(){};
	
	public BST(ArrayList<T> list){
		
		for (int i = 0; i < list.size(); ++i){
			
			this.add(list.get(i));
		}
	}
	
	//Loops through tree using direction to determine to go
	//left or right in the tree to find the place to add the 
	//element
	public void add(T obj) throws IllegalArgumentException{
		
		Node<T> node = tree;
		int direction = 0;        //negative is left, positive is right, 0 is do nothing
		
		if (tree == null){
			
			tree = new Node<T>(obj);
			return;
		}
		
		do{
			node = node.getDirection(direction);
			
			if (obj.compareTo(node.get()) == 0){
				
				throw new IllegalArgumentException();
			}
			else if (0 > obj.compareTo(node.get())){
				
				direction = -1;
			}
			else{
				
				direction = 1;
			}
		} while (node.getDirection(direction) != null);
		
		node.setDirection(direction, new Node<T>(obj));
	}
	
	//Searches the tree for the inputted object and throws 
	//a NoSuchElementException if the object is not in the tree
	public Node<T> search(T obj) throws NoSuchElementException{
		
		Node<T> node = tree;
		int direction = 0;        //negative is left, positive is right, 0 is do nothing
		
		do{
			
			node = node.getDirection(direction);
			
			if (obj.compareTo(node.get()) == 0){
				
				return node;
			}
			else if (0 > obj.compareTo(node.get())){
				
				direction = -1;
			}
			else{
				
				direction = 1;
			}
			
		} while (node.getDirection(direction) != null);

		throw new NoSuchElementException();
	}
	
	//Deletes the inputted object from the tree or throws a
	//NoSuchElementException if the object is not in the tree
	public boolean delete(T obj) throws NoSuchElementException{
		
		Node<T> node = tree;
		Node<T> parent = null;
		int direction = 0;        //negative is left, positive is right, 0 is do nothing
		boolean check = false;    //Used to see if the while check has been checked yet
		
		//If there is no tree throw exception
		if(node == null){
			
			throw new NoSuchElementException();
		}
		
		
		do{
			
			//On first pass parent should be null,
			//after that it should follow node
			if (check){
				
				parent = node;
			}
			
			node = node.getDirection(direction);
			
			if (obj.compareTo(node.get()) == 0){
				
				break;
			}
			else if (0 > obj.compareTo(node.get())){
				
				direction = -1;
			}
			else{
				
				direction = 1;
			}
			
			check = true;
			
		} while (node.getDirection(direction) != null);
		
		/*When the loop breaks, node is the node to be deleted
		 *and parent is its parent. Or, if the obj wasn't found, 
		 *node will be not be equal to obj*/
		
		//Checks to see if the obj is actually stored in this node.
		if (obj.compareTo(node.get()) != 0){
			
			throw new NoSuchElementException();
		}
		
		//If the node has exactly one child or is a leaf
		if (node.hasOneOrFewerChildren()){
			
			if(parent == null){
				
				tree = node.getSingleNode();
				return true;
			}
			
			if(node.getRight() != null){
				
				parent.setDirection(direction, node.getRight());
				node = null;
				return true;
			}
			
			else{
				
				parent.setDirection(direction, node.getLeft());
				node = null;
				return true;
			}
		}
		
		//If the node has 2 children
		else{
			
			Node<T> predecessor = node.getLeft();   //node's in order predecessor
			Node<T> parentPred = node; 				//predecessor's parent
			int directionPred = -1;
			
			//Runs until predecessor is node's in-order predecessor 
			while(predecessor.getRight() != null){
				
				parentPred = predecessor;
				predecessor = predecessor.getRight();
				directionPred = 1;
			}
			
			node.set(predecessor.get());
			
			//If predecessor has children, it would be a left child
			if (predecessor.getLeft() != null){
				
				parentPred.setDirection(directionPred, predecessor.getLeft());
			}
			predecessor = null;
			return true;
		}
	}
	
}
