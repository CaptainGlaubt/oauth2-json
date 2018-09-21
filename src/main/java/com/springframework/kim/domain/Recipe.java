package com.springframework.kim.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Recipe {
    @OneToMany(
        cascade                         = CascadeType.ALL,
        mappedBy                        = "recipe"
    )
    private Set<Ingredient> ingredients = new HashSet<>();
    @ManyToMany
    @JoinTable(
        name                            = "recipe_category",
        joinColumns                     = @JoinColumn(name = "recipe_id") ,
        inverseJoinColumns              = @JoinColumn(name = "category_id")
    )
    private Set<Category>   categories  = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long            id;
    private String          description;
    private Integer         prepTime;
    private Integer         cookTime;
    private Integer         servings;
    private String          source;
    private String          url;
    @Lob
    private String          directions;
    @Lob
    private Byte[]          image;
    @Enumerated(value = EnumType.STRING)
    private Difficulty      difficulty;
    @OneToOne(cascade = CascadeType.ALL)
    private Notes           notes;

    public Recipe addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        ingredient.setRecipe(this);

        return this;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
