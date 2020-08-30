package queuepackage;

public class EmptyQueueException extends RuntimeException{

	EmptyQueueException(){
		super();
	}
	EmptyQueueException(Throwable cause){
		super(cause);
	}
	EmptyQueueException(String s){
		super(s);
	}
}
