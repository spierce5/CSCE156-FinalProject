package entities;

import java.util.Iterator;
import java.util.Comparator;

public class InvoiceList implements Iterable<Invoice>{
	private InvoiceNode<Invoice> start;
	private int size; 
	private Comparator<Invoice> comp;
	
	public InvoiceList() {
		Comparator<Invoice> comp = new TotalComparator();
		this.start = null;
		this.size = 0;
		this.comp = comp;
	}

	public InvoiceNode<Invoice> getStart() {
		return start;
	}

	public void setStart(InvoiceNode<Invoice> start) {
		this.start = start;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Comparator<Invoice> getComp() {
		return comp;
	}

	public void setComp(Comparator<Invoice> comp) {
		this.comp = comp;
	}

	@Override
	public Iterator<Invoice> iterator() {
		return new InvoiceListIterator(this);
	}
	
	class InvoiceListIterator implements Iterator<Invoice>{	
		InvoiceNode<Invoice> currentNode;
		public InvoiceListIterator(InvoiceList list) { 
		        currentNode = list.start; 
		    } 
		

		@Override
		public boolean hasNext() {
			return currentNode != null;
		}

		@Override
		public Invoice next() {
			Invoice invoice = currentNode.getInvoice();
			currentNode = currentNode.getNextNode();
			return invoice;
		}
		public InvoiceNode<Invoice> getCurrent(){
			return currentNode;
		}
	}
	
	public void add(Invoice item) {
		InvoiceNode<Invoice> newNode = new InvoiceNode<Invoice>(item);
		if(start == null) {
			size++;
			newNode.setNextNode(null);
			start = newNode; 
		}
		else if(size == 1) {
			size++;
			if(comp.compare(newNode.getInvoice(), start.getInvoice()) > 0) {
				start.setNextNode(newNode);
				newNode.setNextNode(null);
			}
			else {
				newNode.setNextNode(start);
				start = newNode; 
			}
		}
		else {
			size++;
			InvoiceListIterator iterator = new InvoiceListIterator(this);
			if(comp.compare(newNode.getInvoice(), start.getInvoice()) > 0) {
				newNode.setNextNode(start);
				start = newNode;
			}
			else if(comp.compare(newNode.getInvoice(), start.getNextNode().getInvoice()) > 0) {
				start.setNextNode(newNode);
				newNode.setNextNode(start.getNextNode());
			}
			else {
				
				boolean nodeSet = false;
				
				while(iterator.hasNext()) {
					InvoiceNode<Invoice> currentNode = iterator.getCurrent();
					if(nodeSet) {
						break;
					}
					if(comp.compare(newNode.getInvoice(), iterator.next()) >= 0) {
						currentNode.setNextNode(newNode);
						newNode.setNextNode(iterator.getCurrent());
						nodeSet = true;
					}
				}
			}
				 
			
		}
	}


	

}
