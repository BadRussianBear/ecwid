package com.carllewis14.repos.ui

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.carllewis14.repos.model.Repo
import com.carllewis14.repos.network.RepoRepository
import com.carllewis14.repos.viewmodels.ListViewModel
import io.reactivex.Maybe
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * To Do.
 */
@RunWith(JUnit4::class)
class ListFragmentTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repoRepository: RepoRepository

    lateinit var listViewModel: ListViewModel


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.listViewModel = ListViewModel(this.repoRepository)
    }

    @Test
    fun getRepos() {

        // Mock API response
        Mockito.`when`(this.repoRepository.getRepositories()).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.anyList<Repo>())
        }

        // Invoke
        this.listViewModel.getRepos()

        // Verify
        Assert.assertNotNull(this.listViewModel.repos.value)
    }


}