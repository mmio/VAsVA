package vava.soltesvasko.lezeckastena.Data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Problem {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;
    private String name ;
    private String grade;
    private String sector;
    private double maximumOverhangDegree;
    @ManyToOne
    @JoinColumn(name="setter_id", referencedColumnName = "id")
   // @CreatedBy
    private Climber setter;
    @JsonBackReference
    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private List<ClimberProblem> myClimbers;
    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private long createdAt;
    private String type;

    private static final Logger logger = LoggerFactory.getLogger(Problem.class);

    public Problem(){}

    public Problem(String name, String grade, String sector, double maximumOverhangDegree, String type)
    {
        super();
        this.name = name;
        this.grade = grade;
        this.sector = sector;
        this.maximumOverhangDegree = maximumOverhangDegree;
        this.type = type;

        logger.trace("Creating new Climber.");
    }

    public Long getId() {
        logger.trace(String.format("Getting id (%d).", this.id));
        return id;
    }
}
