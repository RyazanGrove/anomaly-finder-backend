package com.ryazangrove.anomaly_finder_backend.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.ryazangrove.anomaly_finder_backend.models.Score;
import com.ryazangrove.anomaly_finder_backend.repository.ScoreRepository;


import java.util.Arrays;
import java.util.List;

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
}
