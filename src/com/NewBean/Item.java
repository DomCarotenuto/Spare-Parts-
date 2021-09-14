package com.NewBean;

public class Item{
	
	
	private ProductBean product = new ProductBean();
	private int quantity;
	
public Item(ProductBean p) {
		
	setProduct(p);
    setQuantity(1);
	}
	
	public ProductBean getProduct() {
		return product;
	}
	public void setProduct(ProductBean p) {
		this.product = p;
	}

	public String getPhoto() {
		return(getProduct().getPhoto());
		
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getCode() {
	    return(getProduct().getCode());
	  }
	
	 public String getName() {
		    return(getProduct().getName());
		}
	 
	 
	 public double getUnitCost() {
		    return(getProduct().getPrice());
		 }
	 
	 public int getNumProduct() {
		    return(quantity);
		  }
	 
	 public void setNumProduct(int n) {
		    this.quantity = n;
		  }
	 
	 public void incrementNumItems() {
		    //setNumItems(getNumItems() + 1);
			  quantity++;
		  }
	 
	 public void decNumItems() {
		    //setNumItems(getNumItems() + 1);
			  quantity--;
		  }
	 
	 public void cancelOrder() {
		 	setNumProduct(0);
		  }
	 
	 public double getTotalCost() {
		    return(getNumProduct() * getUnitCost());
		  }
	
	
	

}
