package entities;

import java.util.Iterator;
import java.util.Comparator;

public class InvoiceList implements Iterable<Invoice> {
	
	private InvoiceNode<Invoice> start;
	private int size; 
	private Comparator<Invoice> comp;
	
	public InvoiceList(Comparator<Invoice> comp) {
		this.start = null;
		this.size = 0;
		this.comp = comp;
	}

	@Override
	public Iterator<Invoice> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void add(Invoice item) {
		InvoiceNode<Invoice> node = new InvoiceNode<Invoice>(item);
		size++;
		if(size == 0) {
			start = node; 
			node.setNextNode(null);
		}
		else if(size == 1) {
			if(comp.compare(node.getInvoice(), start.getInvoice()) >= 0) {
				start.setNextNode(node);
				node.setNextNode(null);
			}
			else {
				node.setNextNode(start);
				start = node; 
			}
		}
		else {
			//TO-DO:: finish add method
		}
	}

}
