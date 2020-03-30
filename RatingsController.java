package testing;

public class RatingController {

	
	public String getRating() {
		//Nær í rating úr db og skilum því rating
		String rating = ""; //fáum þetta rating úr db 
		return rating;
	}
	
	
	public String setRating(String rating) {
		//má ekki vera ákveðinn langur t.d
		if(rating.length()>50) {
			return null;
		}
		return rating;
	}
	
	public double getStar(double star) {
		
	if(star<=5.0 && star > 0.0 ) {
		return star;
	}
	else
		return (Double) null;
	}
}
