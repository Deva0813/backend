package org.filmrental.com.service;

import java.util.List;
import java.util.Optional;

import org.filmrental.com.dao.ILanguageDao;
import org.filmrental.com.model.Actor;
import org.filmrental.com.model.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageServiceImpl implements ILanguageService{
	
	@Autowired
	private ILanguageDao languageDao;
	
	
	@Override
	public Language addLanguageDetails(Language language) {
	    Language lang= languageDao.save(language);
		return lang;
	}


	@Override
	public List<Language> getAllLanguage() {
		return (List<Language>) languageDao.findAll();
	}


	@Override
	public Language getLanguageById(Byte languageId) {
		Optional<Language> optional = languageDao.findById(languageId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}


	@Override
	public boolean deleteLanguage(Byte languageId) {
		if(getLanguageById(languageId)!=null) {
			languageDao.deleteById(languageId);
			return true;
		}
		return false;
	}


	@Override
	public Language updateLanguage(Language language) {
		return addLanguageDetails(language);
	}

}
