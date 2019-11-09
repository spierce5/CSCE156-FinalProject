package entities;

public class InvoiceNode<T> {
	
	private Invoice invoice;
	private InvoiceNode<T> nextNode;

	public InvoiceNode() {
		this.invoice = null;
		this.nextNode = null;
	}
	
	public InvoiceNode(Invoice invoice) {
		this.invoice = invoice;
		this.nextNode = null;
	}
	
	public InvoiceNode(Invoice invoice, InvoiceNode<T> next) {
		this.invoice = invoice;
		this.nextNode = next;
	}
	
	public Invoice getInvoice() {
		return invoice;
	}
	
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
	public InvoiceNode<T> getNextNode() {
		return nextNode;
	}
	
	public void setNextNode(InvoiceNode<T> nextNode) {
		this.nextNode = nextNode;
	}

}
