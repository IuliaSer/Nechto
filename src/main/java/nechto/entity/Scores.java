package nechto.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nechto.enums.Status;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SCORES")
public class Scores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;

    private float scores;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "SCORES_STATUS", joinColumns = @JoinColumn(name = "scores_id"))
    @Column(name = "status_id")
    @ElementCollection(targetClass = Status.class)
    private List<Status> statuses;
}
