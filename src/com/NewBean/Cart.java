package com.NewBean;
import java.util.ArrayList;




public class Cart {

	private ArrayList<Item> products;
	
	
	public Cart() {
		products = new ArrayList<Item>();
	
	}
	
	public void addProduct(ProductBean product) {
		Item order;
		 for(int i=0; i<products.size(); i++) {
		      order = products.get(i);
		      if (order.getCode()==(product.getCode())) {
		    	order.incrementNumItems();
		        return;
		      }
		    }
		    products.add(new Item(product));
	}
	
	public void delProduct(ProductBean product) {
		Item order;
		 for(int i=0; i<products.size(); i++) {
		      order = products.get(i);
		      if (order.getCode()==(product.getCode())) {
		    	  if(order.getQuantity()>1)
		    	order.decNumItems();
		    	  else 
		    		  if(order.getQuantity()==1) {
		    		  products.remove(products.get(i));
		    	  }
		    	  
		    	  
		        return;
		      }
		    }
		    products.add(new Item(product));
	}
	
	
	
	public synchronized void setNumOrdered(int itemID, int numOrdered) {
	    for(int i=0; i<products.size(); i++) {
	      Item order = products.get(i);
	      if (order.getCode()==(itemID)) {
	        if (numOrdered <= 0) {
	          products.remove(products.get(i));
	        } else {
	          order.setQuantity(numOrdered);
	        }
	      }
	    }
	  }
	
	public void deleteProduct(ProductBean product) {
		for(Item prod : products) {
			if(prod.getCode() == product.getCode()) {
				products.remove(prod);
				break;
			}
		}
 	}
	
	public ArrayList<Item> getProducts() {
		return  (ArrayList<Item>) products;
	}
	
	public float getTotale() {
		float total=0;
		for(int i=0;i<products.size();i++) {
			
			total+=products.get(i).getTotalCost();
			
		}
		
		return total;
	}
	
	public int totalNumCart() {
		int totale=0;
		for(int i=0;i<products.size();i++) {
			totale+=products.get(i).getQuantity();
		}
		
		return totale;
	}
	
	
}
