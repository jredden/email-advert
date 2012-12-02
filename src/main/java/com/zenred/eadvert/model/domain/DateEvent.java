package com.zenred.eadvert.model.domain;

// pojo

public class DateEvent {

		private String CampaignName;
		private String Name;
		private String EventName;
		private String DateSegmentOne;
		private String DateSegmentTwo;
		
		/**
		 * @return the campaignName
		 */
		public String getCampaignName() {
			return CampaignName;
		}
		/**
		 * @param campaignName the campaignName to set
		 */
		public void setCampaignName(String campaignName) {
			CampaignName = campaignName;
		}
		/**
		 * @return the name
		 */
		public String getName() {
			return Name;
		}
		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			Name = name;
		}
		/**
		 * @return the eventName
		 */
		public String getEventName() {
			return EventName;
		}
		/**
		 * @param eventName the eventName to set
		 */
		public void setEventName(String eventName) {
			EventName = eventName;
		}
		/**
		 * @return the dateSegmentOne
		 */
		public String getDateSegmentOne() {
			return DateSegmentOne;
		}
		/**
		 * @param dateSegmentOne the dateSegmentOne to set
		 */
		public void setDateSegmentOne(String dateSegmentOne) {
			DateSegmentOne = dateSegmentOne;
		}
		/**
		 * @return the dateSegmentTwo
		 */
		public String getDateSegmentTwo() {
			return DateSegmentTwo;
		}
		/**
		 * @param dateSegmentTwo the dateSegmentTwo to set
		 */
		public void setDateSegmentTwo(String dateSegmentTwo) {
			DateSegmentTwo = dateSegmentTwo;
		}
		
		public String toString(){
			return new StringBuffer().append('\n')
			.append("CampaignName:"+CampaignName+"\n")
			.append("Name:"+Name+"\n")
			.append("EventName:"+EventName+"\n")
			.append("DateSegmentOne:"+DateSegmentOne+"\n")
			.append("DateSegmentTwo:"+DateSegmentTwo+"\n")
			.toString();
		}
}
