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
			newNode.setNextNode(null);
			start = newNode;
		}
		else if(size == 1) {
			if(comp.compare(newNode.getInvoice(), start.getInvoice()) < 0) {
				start.setNextNode(newNode);
				newNode.setNextNode(null);
			}
			else {
				newNode.setNextNode(start);
				start = newNode;
			}
		}
		else {
			InvoiceNode<Invoice> currentNode = start;
			InvoiceNode<Invoice> previousNode = null;
			
			if(comp.compare(newNode.getInvoice(), start.getInvoice()) > 0) {
				newNode.setNextNode(start);
				start = newNode;
			}
			else {
				while(currentNode != null && comp.compare(newNode.getInvoice(), currentNode.getInvoice()) < 0) {
					previousNode = currentNode;
					currentNode = currentNode.getNextNode();
					if(currentNode != null) {
						if(comp.compare(newNode.getInvoice(), currentNode.getInvoice()) > 0) {
							newNode.setNextNode(currentNode);
							previousNode.setNextNode(newNode);
						}
						
					}
					else {
						previousNode.setNextNode(newNode);
						newNode.setNextNode(null);
					}
				}
			}

			
			
			
			
			
			
			/*
			 while(currentNode != null &&  comp.compare(newNode.getInvoice(), currentNode.getInvoice()) > 0) {
				previousNode = currentNode;
				currentNode = currentNode.getNextNode();
				if(previousNode != null){
					previousNode.setNextNode(newNode);
				}
				newNode.setNextNode(currentNode);
			}
			 */
		}
		size++;
	}




}
