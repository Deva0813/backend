package org.filmrental.com.service;

import java.util.List;

import org.filmrental.com.model.Language;
import org.springframework.stereotype.Service;

public interface ILanguageService {
	public Language addLanguageDetails(Language language);
	public List<Language> getAllLanguage();
	public Language getLanguageById(Byte languageId);
	public boolean deleteLanguage(Byte languageId);
	public Language updateLanguage(Language language);
	
}
