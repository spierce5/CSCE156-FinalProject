package entities;

import java.util.Comparator;

public class TotalComparator implements Comparator<Invoice> {

	@Override
	public int compare(Invoice invoice1, Invoice invoice2) {
		if(invoice1.getInvoiceTotal() > invoice2.getInvoiceTotal()) {
			return 1;
		}
		else if(invoice1.getInvoiceTotal() == invoice2.getInvoiceTotal()) {
			return 0;
		}
		else {
			return -1;
		}
	}

}
