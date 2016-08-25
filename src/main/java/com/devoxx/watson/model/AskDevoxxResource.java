/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.devoxx.watson.model;

/**
 * Contains metadata from a given document held in the Devoxx corpus.
 *
 * @author James Weaver
 */
public class AskDevoxxResource {
  private String id;
  private String score;
  private String label;
  private String authors;
  private String language;
  private String publicationDate;
  private String resourceUrl;
  private String thumbnailUrl;
  private String thumbnailKeywords;
  private String emotions;
  private String sentiment;

  public AskDevoxxResource() {
  }

  public AskDevoxxResource(String id, String score, String label, String authors, String language, String publicationDate, String resourceUrl, String thumbnailUrl, String thumbnailKeywords, String emotions, String sentiment) {
    this.id = id;
    this.score = score;
    this.label = label;
    this.authors = authors;
    this.language = language;
    this.publicationDate = publicationDate;
    this.resourceUrl = resourceUrl;
    this.thumbnailUrl = thumbnailUrl;
    this.thumbnailKeywords = thumbnailKeywords;
    this.emotions = emotions;
    this.sentiment = sentiment;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getScore() {
    return score;
  }

  public void setScore(String score) {
    this.score = score;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getAuthors() {
    return authors;
  }

  public void setAuthors(String authors) {
    this.authors = authors;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(String publicationDate) {
    this.publicationDate = publicationDate;
  }

  public String getResourceUrl() {
    return resourceUrl;
  }

  public void setResourceUrl(String resourceUrl) {
    this.resourceUrl = resourceUrl;
  }

  public String getThumbnailUrl() {
    return thumbnailUrl;
  }

  public void setThumbnailUrl(String thumbnailUrl) {
    this.thumbnailUrl = thumbnailUrl;
  }

  public String getThumbnailKeywords() {
    return thumbnailKeywords;
  }

  public void setThumbnailKeywords(String thumbnailKeywords) {
    this.thumbnailKeywords = thumbnailKeywords;
  }

  public String getEmotions() {
    return emotions;
  }

  public void setEmotions(String emotions) {
    this.emotions = emotions;
  }

  public String getSentiment() {
    return sentiment;
  }

  public void setSentiment(String sentiment) {
    this.sentiment = sentiment;
  }

  @Override
  public String toString() {
    return "AskDevoxxResource{" +
        "id='" + id + '\'' +
        ", score='" + score + '\'' +
        ", label='" + label + '\'' +
        ", authors='" + authors + '\'' +
        ", language='" + language + '\'' +
        ", publicationDate='" + publicationDate + '\'' +
        ", resourceUrl='" + resourceUrl + '\'' +
        ", thumbnailUrl='" + thumbnailUrl + '\'' +
        ", thumbnailKeywords='" + thumbnailKeywords + '\'' +
        ", emotions='" + emotions + '\'' +
        ", sentiment='" + sentiment + '\'' +
        '}';
  }
}
