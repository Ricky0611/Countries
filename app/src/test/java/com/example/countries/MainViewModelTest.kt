package com.example.countries

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.countries.data.Country
import com.example.countries.db.MainRepository
import com.example.countries.ui.viewmodel.MainViewModel
import com.example.countries.utils.CountryViewModelFactory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get: Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Mock
    lateinit var repository: MainRepository

    @Mock
    lateinit var countryListObserver: Observer<List<Country>>

    @Before
    fun setUp() {
        viewModel = MainViewModel(repository)
    }

    @Test
    fun testMainViewModel() {
        val mainViewModel = CountryViewModelFactory(repository).create(MainViewModel::class.java)
        Assert.assertEquals(mainViewModel.javaClass, viewModel.javaClass)
    }

    @Test
    fun testGetCountries() {
        runTest(StandardTestDispatcher()) {
            val gson = Gson()
            val typeToken = object : TypeToken<List<Country>>() {}
            val mockResponse = Response.success(gson.fromJson(TestConstant.SUCCESS_RESPONSE, typeToken))

            Mockito.`when`(repository.getCountries()).thenReturn(mockResponse)

            viewModel.countryList.observeForever(countryListObserver)
            viewModel.getCountries()
            val expectedResult = gson.fromJson(TestConstant.SUCCESS_RESPONSE, typeToken)

            verify(repository).getCountries()
            verify(countryListObserver).onChanged(expectedResult)

            viewModel.countryList.removeObserver(countryListObserver)
        }
    }
}
