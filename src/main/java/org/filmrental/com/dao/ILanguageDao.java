package org.filmrental.com.dao;

import org.filmrental.com.model.Language;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILanguageDao extends CrudRepository<Language, Byte> {
	public Language findByName(String languageName);
}
