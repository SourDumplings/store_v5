package cn.itcast.store.domain;

public class OrderItem
{
	private String itemId;
	private int quantity;
	private Double total;
	// 对象对应对象，Product和Order可以携带更多的数据
	private Product product;
	private Order order;
	public String getItemId()
	{
		return itemId;
	}
	public void setItemId(String itemId)
	{
		this.itemId = itemId;
	}
	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity(int i)
	{
		this.quantity = i;
	}
	public Double getTotal()
	{
		return total;
	}
	public void setTotal(Double total)
	{
		this.total = total;
	}
	public Product getProduct()
	{
		return product;
	}
	public void setProduct(Product product)
	{
		this.product = product;
	}
	public Order getOrder()
	{
		return order;
	}
	public void setOrder(Order order)
	{
		this.order = order;
	}
	
	
}
