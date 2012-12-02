package com.zenred.eadvert.model.domain;

public class TemplateVector {

		private String templateName;
		private String uid;
		private String graphicUid;
				
		/**
		 * @return the templateName
		 */
		public String getTemplateName() {
			return templateName;
		}
		/**
		 * @param templateName the templateName to set
		 */
		public void setTemplateName(String templateName) {
			this.templateName = templateName;
		}
		/**
		 * @return the uid
		 */
		public String getUid() {
			return uid;
		}
		/**
		 * @param uid the uid to set
		 */
		public void setUid(String uid) {
			this.uid = uid;
		}
		/**
		 * @return the graphicUid
		 */
		public String getGraphicUid() {
			return graphicUid;
		}
		/**
		 * @param graphicUid the graphicUid to set
		 */
		public void setGraphicUid(String graphicUid) {
			this.graphicUid = graphicUid;
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		public String toString(){
			return new StringBuffer().append('\n')
			.append("templateName:"+templateName+"\n")
			.append("uid:"+uid+"\n")
			.append("graphicUid:"+graphicUid+"\n")
			.toString();
		}
}
