# consumer-android

1. [StarWarsApp](#starwarsapp)
   * [Architecture](#architecture)
   * [API description](#api)
   * [Data providing](#dataproviding)
   * [DependencyInjection](#dependencyinjection)
   * [AppTheme](#applicationtheme)
   * [ComposeUI](#composeui)
   * [Navigation](#navigation)
   * [Testing]()

# StarWarsApp

## Architecture

   The SWA(StarWarsApplication) is based on the clean architecture with MVI. The SWA is a single 
activity application with navigation between screens. The app is ready for improvements such as 
adding new screens, repositories. 
   The SWA allows device rotation, works with edge-to-edge.
   The ViewModel level has [BaseViewModel] that is in charge of keeping the safeViewModelScope, 
handling error that occurs on the view model side

1.The DataLayer keeps models, data sources, the generated data base and the repository.
2.The Presentation layer keeps view models, the navigation graph, themes, screens and ui components.
   

## Api

   The SWA is adjusted to works with GraphQL API schema("https://rickandmortyapi.com/graphql"),
Only two types of queries is available now. To check it see folder main/graphql/*.

## DataProviding

   The SWA has implemented two ways to fetch the data. Based on [RemotePeopleDataSource] 
and [RemotePersonDataSource].
1. By using the GraphQL API schema("https://rickandmortyapi.com/graphql"). 
The implementation with prefix **Apollo**
2. By using the local generated data base. Used for case when main data source in not working. 
The implementation with prefix **Mocked**

   There is no local saved data, the SWA uses only session cache.
   The SWA uses the OneSourceOfTruth and keep all the data in the [PeopleRepository] class. 
The repository has its own CoroutineScope to avoid operation interuption when view model scope is canceled.
   There is only one model to work with [Person]


# DependencyInjection

   The SWA supports DependencyInjection via Hilt DI, recommended to using by Google. 
   There are two DI Modules: Binders and Providers
   There are two Annotation classes to work with different dataSource implementations
   use @Apollo annotation to work with API
   use @MockedData annotation to work with local provided data

## ApplicationTheme

   The SWA has its own Theme. [StarWarsAppTheme] includes:
1. Dimens control -> [StarWarsDimens]
2. Colors control -> [StarWarsColors]
3. Typography control -> [StarWarsTypography]

   Each block is divided into separate zone to control different screens

## ComposeUI

   The SWA uses the Compose ui tool.
   Each screen has a couple of states, that controlled by ScreenUIState. A screen has three states: 
Loading, Success, Error. Each state has its own screen.
   All components, UIActions and UIStates that related to the screen are placed in the folder 
with a screen name.

# Navigation

   The navigation in the SWA is provided by Compose navigation tool.
   There are files: [AppScreens], [AppNavigation], [NavAnimation] to describe a NavGraph 
and transitions between screens.

# Testing
   
   The SWA is covered by Unit testing.
   The Main testing library is Mockk. That library is adjusted to work with flow, coroutines, 
extentions, objects and other Kotlin objects.
   