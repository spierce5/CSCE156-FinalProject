package entities;

import java.util.Comparator;

public class TotalComparator implements Comparator<Invoice> {

	
	public TotalComparator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Invoice o1, Invoice o2) {
		if(o1.getInvoiceTotal() > o2.getInvoiceTotal()) {
			return 1;
		}
		else if(o1.getInvoiceTotal() == o2.getInvoiceTotal()) {
			return 0;
		}
		else {
			return -1;
		}
	}

}
