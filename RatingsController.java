public class RatingController {
    public String getRating(String rating) {
	//Skilar rating sem double tölu og einhvern texta
	return rating;
    }
    
    public String setRating(String rating) {
	//má ekki vera ákveðinn langur t.d
	if(rating.length()>50) {
		//kannski try catch?
	}
	return rating;
    }
    
    public double getStar(double star) {
	if(star < 5.0 || star > 0.0 ) {
	    return star;
	}
	else
	    return (Double) null;
	}
}
