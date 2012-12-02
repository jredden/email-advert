package com.zenred.eadvert.service;

import java.text.MessageFormat;

import java.util.List;

import com.zenred.eadvert.dao.FileIODao;
import com.zenred.eadvert.dao.TemplateDao;
import com.zenred.eadvert.exception.DataAccessException;
import com.zenred.eadvert.model.domain.TemplateVector;

public class TemplateService {

	private TemplateDao templateDao;
	private FileIODao fileIODao;
	private String templateBaseFolder;
	private String imagesBasePath;
	private String template;

	/**
	 * @param templateDao
	 *            the templateDao to set
	 */
	public void settemplateDao(TemplateDao templateDao) {
		this.templateDao = templateDao;
	}

	/**
	 * @param fileIODao
	 *            the fileIODao to set
	 */
	public void setFileIODao(FileIODao fileIODao) {
		this.fileIODao = fileIODao;
	}

	/**
	 * @param templateBaseFolder
	 *            the templateBaseFolder to set
	 */
	public void setTemplateBaseFolder(String templateBaseFolder) {
		this.templateBaseFolder = templateBaseFolder;
	}

	/**
	 * @param imagesBasePath
	 *            the imagesBasePath to set
	 */
	public void setImagesBasePath(String imagesBasePath) {
		this.imagesBasePath = imagesBasePath;
	}

	/**
	 * @param template
	 *            the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}

	public List<String> fetchTemplatesNotInCampaign(String campaignName) {
		List<String> templates = templateDao
				.fetchTemplatesCestnPasEnCampaign(campaignName);
		return templates;
	}

	public List<String> fetchTemplatesInCampaign(String campaignName) {
		List<String> templates = templateDao
				.fetchTemplatesCestEnCampaign(campaignName);
		return templates;
	}

	public void attachTemplateToCampaign(String templateName,
			String campaignName) {
		templateDao.associateTemplateToCampaign(templateName, campaignName);
	}

	public void detachTemplateToCampaign(String templateName,
			String campaignName) {
		templateDao
				.dissasociateTemplateFromCampaign(templateName, campaignName);
	}

	public String readTemplateContentsAssociatedToCampaign(String campaignName)
			throws DataAccessException {
		String totalXmlTemplate;
		StringBuffer workingTemplates = new StringBuffer();

		List<TemplateVector> templateVectorList = templateDao
				.readTemplatesAssociatedToCampaign(campaignName);
		for (TemplateVector templateVector : templateVectorList) {
			String path = templateBaseFolder + templateVector.getUid()
					+ templateVector.getTemplateName();
			String innerTemplate = MessageFormat.format(fileIODao
					.readStringFileUsingURI(path), new Object[] { templateVector
					.getGraphicUid() });
			// String cookedTemplate = MessageFormat.format(template, new
			// Object[]{templateVector.getGraphicUid(), innerTemplate});

			workingTemplates.append(innerTemplate);
		}
		totalXmlTemplate = MessageFormat.format(template, new Object[] {
				imagesBasePath, workingTemplates });
		return totalXmlTemplate;
	}

}
