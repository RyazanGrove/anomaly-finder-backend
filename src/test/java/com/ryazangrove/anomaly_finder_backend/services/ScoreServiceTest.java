package com.ryazangrove.anomaly_finder_backend.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.ryazangrove.anomaly_finder_backend.exceptions.ScoreNotFoundException;
import com.ryazangrove.anomaly_finder_backend.models.Score;
import com.ryazangrove.anomaly_finder_backend.repository.ScoreRepository;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
public class ScoreServiceTest {
    
    @Mock
    private ScoreRepository scoreRepository;

    @InjectMocks
    private ScoreService scoreService;

    @Test
    public void shouldReturnListOfScores() {
        // Arrange
        when(scoreRepository.findAll()).thenReturn(Arrays.asList(
                new Score(101l, 21000l, "John Doe"),
                new Score(102l, 5600l, "Jane Doe")
        ));

        // Act
        List<Score> scores = scoreService.getScores();

        // Assert
        verify(scoreRepository, times(1)).findAll();
        assertEquals(scores.size(), 2);
        Score score0 = scores.get(0);
        assertEquals(score0.getId(), 101l);
        assertEquals(score0.getScore(), 21000l);
        assertEquals(score0.getNickname(), "John Doe");
        Score score1 = scores.get(1);
        assertEquals(score1.getId(), 102l);
        assertEquals(score1.getScore(), 5600l);
        assertEquals(score1.getNickname(), "Jane Doe");
    }

    @Test
    public void shouldSaveScore() {
        Score score = Score.builder()
            .id(0l).score(1000l)
            .nickname("John Doe").build();

        scoreService.saveScore(score);

        verify(scoreRepository, times(1)).save(score);
    }

    @Test
    public void shouldReturnScoreById() {
        Long scoreId = 100l;
        Score score = Score.builder().id(scoreId).score(10000l).nickname("John Doe").build();
        when(scoreRepository.findById(scoreId)).thenReturn(Optional.of(score));

        Score returnScore = scoreService.getScore(scoreId);

        verify(scoreRepository, times(1)).findById(scoreId);
        assertEquals(returnScore.getId(), score.getId());
        assertEquals(returnScore.getScore(), score.getScore());
        assertEquals(returnScore.getNickname(), score.getNickname());
    }

    @Test
    public void shouldThrowExceptionWhenScoreByIdNotFound() {
        Long scoreId = 100l;
        when(scoreRepository.findById(scoreId)).thenThrow(new ScoreNotFoundException(scoreId));

        try{
            scoreService.getScore(scoreId);
        } catch (ScoreNotFoundException e) {
            assertEquals(e.getMessage(), "Could not find score with id " + scoreId);
        }

        verify(scoreRepository, times(1)).findById(scoreId);
    }
}
