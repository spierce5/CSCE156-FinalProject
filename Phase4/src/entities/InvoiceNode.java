package entities;

public class InvoiceNode<Invoice> {
	
	private Invoice invoice;
	private InvoiceNode<Invoice> nextNode;

	public InvoiceNode() {
		this.invoice = null;
		this.nextNode = null;
	}
	
	public InvoiceNode(Invoice invoice) {
		this.invoice = invoice;
		this.nextNode = null;
	}
	
	public InvoiceNode(Invoice invoice, InvoiceNode<Invoice> next) {
		this.invoice = invoice;
		this.nextNode = next;
	}
	
	public Invoice getInvoice() {
		return invoice;
	}
	
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
	public InvoiceNode<Invoice> getNextNode() {
		return nextNode;
	}
	
	public void setNextNode(InvoiceNode<Invoice> nextNode) {
		this.nextNode = nextNode;
	}

}
