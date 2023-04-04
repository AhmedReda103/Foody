# Foody
foody app target people that search for specific recipes , how to make it and what is an ingredients of it , save his favourite recipes in favourites 
using spoonacular api to get recipes and ingredients and more , cache response from api to local database using Room

# Preview

<div>
<img src="https://user-images.githubusercontent.com/95850640/229937417-d0c9040a-3c92-4669-94b0-8a38a48692c1.jpg" width="140" height="320" >
<img src="https://user-images.githubusercontent.com/95850640/229937526-882acaf6-eaf1-4283-aaa1-5544d548732b.jpg" width="140" height="320" >
<img src="https://user-images.githubusercontent.com/95850640/229939979-da830cb6-f759-4a16-ab72-7b1eab3e5052.jpg" width="140" height="320" >
<img src="https://user-images.githubusercontent.com/95850640/229940089-4ec54dc0-6aa3-4e26-985f-cc2ab02df070.jpg" width="140" height="320" >
<img src="https://user-images.githubusercontent.com/95850640/229940098-3d4dfd1f-2027-4747-89f8-cf34def6d1d1.jpg" width="140" height="320" >
<img src="https://user-images.githubusercontent.com/95850640/229940112-b4bbdd07-2738-4d56-8016-cd33078c9d93.jpg" width="140" height="320" >
<img src="https://user-images.githubusercontent.com/95850640/229940135-b716c905-2f4b-41ae-be8e-df4e2510f265.jpg" width="140" height="320" >
</div>

<div>
<img src="https://user-images.githubusercontent.com/95850640/229941675-db79c1c0-99f2-4aa3-8069-7e21ab4e0836.jpg" width="140" height="320" >
<img src="https://user-images.githubusercontent.com/95850640/229940856-aceb172b-dded-46da-8a06-c0426b201bb2.jpg" width="140" height="320" >
<img src="https://user-images.githubusercontent.com/95850640/229940826-dbf02468-f60f-4aa9-b148-2cadcfc707b1.jpg" width="140" height="320" >
<img src="https://user-images.githubusercontent.com/95850640/229940831-0669bf5c-cf5a-4212-9b7a-6df1482fcc26.jpg" width="140" height="320" >
<img src="https://user-images.githubusercontent.com/95850640/229940841-82956095-9611-40ba-9955-91b5579db579.jpg" width="140" height="320" >
<img src="https://user-images.githubusercontent.com/95850640/229941094-7cf8dcdc-cb6b-4eca-bcea-40921995e8df.jpg" width="140" height="320" >
</div>



# Libraries and technologies used

- Navigation component 

- Coroutines 

- Data binding 

- View binding

- ROOM Database

- Retrofit

- Dependency Injection - Dagger-Hilt

- DataStore Preferences

- Flow 

- LiveData

- Material Components

- ViewModel

- Create Contextual Action Mode

- Light/dark mode

- Motion Layout


## API Reference

#### Get all items

```http
  GET https://api.spoonacular.com/recipes/complexSearch
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |
| `number`  | `string` | 	The number of expected results          |
| `type`    | `string` | The type of recipe         |
| `diet`    | `string` | You can specify multiple diets separated |
| `addRecipeInformation`| `Boolean`  | If set to true, you get more information about the recipes returned |
| `fillIngredients`| `Boolean`  | If set to true,Add information about the ingredients  |

#### Search through thousands of recipes

```http
  GET https://api.spoonacular.com/recipes/complexSearch
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |
| `number`  | `string` | 	The number of expected results          |
| `addRecipeInformation`| `Boolean`  | If set to true, you get more information about the recipes returned |
| `fillIngredients`| `Boolean`  | If set to true,Add information about the ingredients  |
| `query` | `string` | The (natural language) recipe search query |



#### Get a random joke that is related to food

```http
  GET https://api.spoonacular.com/food/jokes/random
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |




## Environment Variables

To run this project, you will need to add `API_KEY`





