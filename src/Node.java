public class Node<T extends Comparable <T>> {
	
	private T data = null;
	private Node<T> left = null;
	private Node<T> right = null;

	public Node(){};
	
	public Node(T obj){
		
		this.data = obj;
	}
	
	public Node(T obj, Node<T> left, Node<T> right, Node<T> parent){
		
		this.data = obj;
		this.left = left;
		this.right = right;
	}
	
	
	public void set(T obj){
		
		this.data = obj;
	}
	public T get(){
		
		return this.data;
	}
	
	public void setLeft(Node<T> node){
		
		this.left = node;
		
	}
	
	public void setRight(Node<T> node){
		
		this.right = node;
	}
	
	public Node<T> getLeft(){
		
		return left;
	}
	
	public Node<T> getRight(){
		
		return right;
	}
	
	public boolean hasChildren(){
		
		return (left != null || right != null);
	}
	
	public boolean hasExactlyOneChild(){
		
		//XOR
		return (left != null || right != null) && !(left != null && right != null);
	}
	
	public boolean hasOneOrFewerChildren(){
		
		return (left == null || right == null);
	}
	
	public Node<T> getSingleNode(){
		
		if(left == null && right != null){
			
			return right;
		}
		else if(left != null && right == null){
			
			return left;
		} 
		else{
			
			return null;
		}
		
	}
	
	public Node<T> getDirection(int direction){
		
		if (direction > 0){
			
			return this.getRight();
		}
		else if(direction < 0){
			
			return this.getLeft();
		}
		else{
			return this;
			
		}
	}
	
	public void setDirection(int direction, Node<T> node){
		
		if(direction < 0){
			
			this.setLeft(node);
		} 
		else if(direction > 0){
			
			this.setRight(node);
		}
		else{
			
			this.set(node.get());
		}
	}
}
