/*****************************************************************
Collects all the trending locations for Twitter and sorts them
by country then town. Saves them to have access.

started March 10, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
package utilities;

import java.util.TreeSet;

import twitter4j.Location;
import twitter4j.ResponseList;

public class TrendLocations {

	/**Collection of locations*/
	private TreeSet<TrendLocation> locations;

	/*****************************************************************
	Constructor. Fills and sorts the collection of locations with the 
	given ResponseList.
	
	@param locals ResponseList<Location> List of trend locations
	 *****************************************************************/
	public TrendLocations (ResponseList<Location> locals) {

		locations = new TreeSet<TrendLocation>();

		for (Location loc: locals) {
			locations.add(new TrendLocation(loc));
		}
	}

	/*****************************************************************
	Returns the collection of locations.
	 *****************************************************************/
	public TreeSet<TrendLocation> getLocations() {
		return locations;
	}
	
	/*****************************************************************
	Returns an array of the trend locations.

	@return TrendLocation[]
	 *****************************************************************/
	public TrendLocation[] getArray() {		
		TrendLocation[] tlArray = new TrendLocation[locations.size()];
		tlArray = locations.toArray(tlArray);
		return tlArray;
	}

	/*****************************************************************
	Prints out each location for testing purposes.

	 *****************************************************************/
//	public void printTrendLocations () {	
//		System.out.println(locations.size());
//		System.out.println(locations);
//	}

	/*****************************************************************
	Specific Location that contains the Twitter Information.
	 *****************************************************************/
	public class TrendLocation implements Comparable{

		/**Code for the Country Location is in*/
		private String countryCode;

		/**Name to be used for comparison*/
		private String compareName;

		/**Location Information*/
		private Location locationTrend;

		/*****************************************************************
		Constructor. Sets up the location based on the country code
		
		@param code String of the country code for the location.
		 *****************************************************************/
		public TrendLocation(String code) {
			countryCode = code;
		}

		/*****************************************************************
		Constructor. Sets up the location based on the Location Information.

		@param place Location from Twitter.
		 *****************************************************************/
		public TrendLocation(Location place) {
			locationTrend = place;
			countryCode = locationTrend.getCountryName();
			if (place.getPlaceCode() != 7) {
				compareName = place.getCountryName();
			} else {
				compareName = place.getCountryName() + " " + place.getName();
			}
		}

		/*****************************************************************
		Returns the country code for the location.
		
		@return String of the country code.
		 *****************************************************************/
		public String getCountryCode () {		
			return countryCode;
		}

		/*****************************************************************
		Sets the location information.
		
		@param loc Location from Twitter.
		 *****************************************************************/
		public void setLocation (Location loc) {		
			locationTrend = loc;
		}

		/*****************************************************************
		Returns the name of the location.
		
		@return String Name of location.
		 *****************************************************************/
		public String getName () {		
			return locationTrend.getName();
		}

		/*****************************************************************
		Gets the town, country name of the location.

		@return String formatted to town, country.
		 *****************************************************************/
		public String getTownName () {		
			return locationTrend.getName() + ", " + locationTrend.getCountryName();
		}

		/*****************************************************************
		Returns the location information.

		@return Location
		 *****************************************************************/
		public Location getLocation () {		
			return locationTrend;
		}

		/*****************************************************************
		toString method formated name: place code
		
		@return String formatted and such.
		 *****************************************************************/
		public String toString() {
			return getName() + ":" + locationTrend.getPlaceCode() + "\n";
		}

		/*****************************************************************
		Returns true if the country codes are equal.
		
		@param obj Object should be country code passed.
		@return boolean true if comparing country codes.
		 *****************************************************************/
		public boolean equals (Object obj) {		
			return countryCode.equals(obj);
		}

		/*****************************************************************
		Comparing the town names and countries.
		
		@param arg0 Object TrendLocation to compare this to that.
		@return int Standard negative, zero, and positive comparisons.
		 *****************************************************************/
		//a negative integer (this < arg0), 
		//zero (this== arg0), or 
		//a positive integer(this > arg0)
		@Override
		public int compareTo(Object arg0) {

			TrendLocation temp = (TrendLocation) arg0;
			if (temp.getCountryCode() == null) {
				return 1;
			}
			if (locationTrend.getPlaceCode() == 19) {
				return -1;
			}
			if (temp.getLocation().getPlaceCode() == 19) {
				return 1;
			}

			return compareName.compareTo(temp.compareName);
		}
	}
}
