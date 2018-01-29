package controller;






public class Booksearch {
	private String Isbn;
	private String Title;
	private String Author;
	private String availability;
	
	 public void setIsbn(String Isbn)
	{
		this.Isbn=Isbn;
	}
   public String getIsbn()
   {
	   return(this.Isbn);
   }
   public void setTitle(String Title)
	{
		this.Title=Title;
	}
  public String getTitle()
  {
	   return(this.Title);
  }
  public void setAuthor(String Author)
	{
		this.Author=Author;
	}
 public String getAuthor()
 {
	   return(this.Author);
 }
 public void setavailability(String availability)
	{
		this.availability=availability;
	}
public String getavailability()
{
	   return(this.availability);
}
}
