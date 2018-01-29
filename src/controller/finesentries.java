package controller;

public class finesentries {
	private String card_id;
	private String fine_amt;
	private String paid;
	
	public void setcard_id(String card_id)
	{
		this.card_id=card_id;
	}
   public String getcard_id()
   {
	   return(this.card_id);
   }


	public void setfine_amt(String fine_amt)
	{
		this.fine_amt=fine_amt;
	}
  public String getfine_amt()
  {
	   return(this.fine_amt);
  }
  public void setpaid(String paid)
	{
		this.paid=paid;
	}
public String getpaid()
{
	   return(this.paid);
}
   
}
