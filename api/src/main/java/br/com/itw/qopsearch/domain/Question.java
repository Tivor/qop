package br.com.itw.qopsearch.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Igor on 31/07/2015.
 */
@Entity
@Table(name = "question")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", precision = 32, scale = 0)
    private Long id;

    @NotNull
    @Size(max = 250)
    @Column(name = "text", nullable = false, length = 250)
    private String text;

}
