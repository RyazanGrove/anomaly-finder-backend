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
        Score score1 = Score.builder()
            .id(101l).score(21000l)
            .nickname("John Doe").build();
        Score score2 = Score.builder()
            .id(102l).score(5600l)
            .nickname("Jane Doe").build();
        when(scoreRepository.findAll()).thenReturn(Arrays.asList(score1, score2));

        // Act
        List<Score> scores = scoreService.getScores();

        // Assert
        verify(scoreRepository, times(1)).findAll();
        assertEquals(2, scores.size());
        assertEquals(score1, scores.get(0));
        assertEquals(score2, scores.get(1));
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
        assertEquals(score, returnScore);
    }

    @Test
    public void shouldThrowExceptionWhenScoreByIdNotFound() {
        Long scoreId = 100l;
        when(scoreRepository.findById(scoreId)).thenThrow(new ScoreNotFoundException(scoreId));

        try {
            scoreService.getScore(scoreId);
        } catch (ScoreNotFoundException e) {
            assertEquals("Could not find score with id " + scoreId, e.getMessage());
        }

        verify(scoreRepository, times(1)).findById(scoreId);
    }
}
