package entities;

import java.io.Serializable;

public class Stock implements Serializable{
	private int productId;
	private int quatily;
	private int shopNo;
	public Stock(int productId, int quatily, int shopNo) {
		this.productId = productId;
		this.quatily = quatily;
		this.shopNo = shopNo;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuatily() {
		return quatily;
	}
	public void setQuatily(int quatily) {
		this.quatily = quatily;
	}
	public int getShopNo() {
		return shopNo;
	}
	public void setShopNo(int shopNo) {
		this.shopNo = shopNo;
	}
	
	
}
