/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizapp.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LE TUNG
 */
public class Question {

    private int id;
    private String content;
    private String hint;
    private String img;
    private Category category;
    private Level level;
    private List<Choice> choices;

    private Question(Builder b) {
        this.id = b.id;
        this.content = b.content;
        this.hint = b.hint;
        this.img = b.img;
        this.category = b.category;
        this.level = b.level;
        this.choices = b.choices;
    }

    public static class Builder {

        private int id;
        private String content;
        private String hint;
        private String img;
        private Category category;
        private Level level;
        private List<Choice> choices;

        public Builder(String content, Category category, Level level) throws Exception {
            if (content.isEmpty() || category == null || this.level == null) {
                throw new Exception("invalid data");
            }
            this.content = content;
            this.category = category;
            this.level = level;
            this.choices = new ArrayList<>();
        }

        public Builder(int id, String content) {
            this.id = id;
            this.content = content;
        }
        
        
        
        public Builder addHint(String s){
            this.hint = s;
            return this;
        }
        
        public Builder addImage (String url){
            this.img = url;
            return this;
        }
        
        public Builder addChoice (Choice choice){
            this.choices.add(choice);
            return this;
        }
        
        public Builder addAllChoice (List<Choice> choices){
            this.choices.addAll(choices);
            return this;
        }
        
        
        public Question build(){
            return new Question(this);
        }
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the hint
     */
    public String getHint() {
        return hint;
    }

    /**
     * @param hint the hint to set
     */
    public void setHint(String hint) {
        this.hint = hint;
    }

    /**
     * @return the img
     */
    public String getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @return the level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * @return the choices
     */
    public List<Choice> getChoices() {
        return choices;
    }

    /**
     * @param choices the choices to set
     */
    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

}
