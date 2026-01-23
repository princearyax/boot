import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
class Product{
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID uuid;
	
	@Embedded
	private Dimensions dimensions; //how it sets?
	
	@UpdateTimestamp
	private Instant lastUpdated;
	
	//getter for all, setter for all except id
	
}

@Embeddable
class Dimensions{
	private Double height;
	private Double width;
	private Double depth;
	//getter setter 
}

enum Status{
	IN_STOCK, OUT_OF_STOCK, DISCONTINUED;
}