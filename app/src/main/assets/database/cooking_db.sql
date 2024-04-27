--
-- File generated with SQLiteStudio v3.4.4 on Thu Apr 18 09:52:13 2024
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: DishType
CREATE TABLE IF NOT EXISTS DishType (dtid INTEGER PRIMARY KEY, typeName TEXT UNIQUE) WITHOUT ROWID;
INSERT INTO DishType (dtid, typeName) VALUES (1, 'Sandwiches & Wraps');
INSERT INTO DishType (dtid, typeName) VALUES (2, 'Soups and Stews');
INSERT INTO DishType (dtid, typeName) VALUES (3, 'Pie');
INSERT INTO DishType (dtid, typeName) VALUES (4, 'BBQ and Grilling');
INSERT INTO DishType (dtid, typeName) VALUES (5, 'Casserole');
INSERT INTO DishType (dtid, typeName) VALUES (6, 'Meats');
INSERT INTO DishType (dtid, typeName) VALUES (7, 'Pasta');
INSERT INTO DishType (dtid, typeName) VALUES (8, 'Pizza');
INSERT INTO DishType (dtid, typeName) VALUES (9, 'Rice and Beans');
INSERT INTO DishType (dtid, typeName) VALUES (10, 'Salad');
INSERT INTO DishType (dtid, typeName) VALUES (11, 'Stir-fry');

-- Table: Ingredient
CREATE TABLE IF NOT EXISTS Ingredient (iid INTEGER PRIMARY KEY, ingredientName TEXT UNIQUE) WITHOUT ROWID;
INSERT INTO Ingredient (iid, ingredientName) VALUES (1, 'Dry Ziti');
INSERT INTO Ingredient (iid, ingredientName) VALUES (2, 'Onion');
INSERT INTO Ingredient (iid, ingredientName) VALUES (3, 'Lean Ground Beef');
INSERT INTO Ingredient (iid, ingredientName) VALUES (4, 'Spaghetti Sauce');
INSERT INTO Ingredient (iid, ingredientName) VALUES (5, 'Provolone Cheese');
INSERT INTO Ingredient (iid, ingredientName) VALUES (6, 'Sour Cream');
INSERT INTO Ingredient (iid, ingredientName) VALUES (7, 'Mozzarella Cheese');
INSERT INTO Ingredient (iid, ingredientName) VALUES (8, 'Parmesan Cheese');
INSERT INTO Ingredient (iid, ingredientName) VALUES (9, 'Bone in Chicken Thigh Fillets');
INSERT INTO Ingredient (iid, ingredientName) VALUES (10, 'Garlic');
INSERT INTO Ingredient (iid, ingredientName) VALUES (11, 'Butter');
INSERT INTO Ingredient (iid, ingredientName) VALUES (12, 'White Rice');
INSERT INTO Ingredient (iid, ingredientName) VALUES (13, 'Chicken Stock');
INSERT INTO Ingredient (iid, ingredientName) VALUES (14, 'Water');
INSERT INTO Ingredient (iid, ingredientName) VALUES (15, 'Paprika Powder');
INSERT INTO Ingredient (iid, ingredientName) VALUES (16, 'Dried Thyme');
INSERT INTO Ingredient (iid, ingredientName) VALUES (17, 'Garlic Powder');
INSERT INTO Ingredient (iid, ingredientName) VALUES (18, 'Onion Powder');
INSERT INTO Ingredient (iid, ingredientName) VALUES (19, 'Salt');
INSERT INTO Ingredient (iid, ingredientName) VALUES (20, 'Black Pepper');
INSERT INTO Ingredient (iid, ingredientName) VALUES (21, 'Colby Jack Cheese');
INSERT INTO Ingredient (iid, ingredientName) VALUES (22, 'Chili Powder');
INSERT INTO Ingredient (iid, ingredientName) VALUES (23, 'Cajun Seasoning');
INSERT INTO Ingredient (iid, ingredientName) VALUES (24, 'Cayenne Pepper Seasoning');
INSERT INTO Ingredient (iid, ingredientName) VALUES (25, 'Flavor Bomb Burger Seasoning');

-- Table: Measurement
CREATE TABLE IF NOT EXISTS Measurement (mid INTEGER PRIMARY KEY, measurementName TEXT UNIQUE) WITHOUT ROWID;
INSERT INTO Measurement (mid, measurementName) VALUES (1, 'Teaspoon');
INSERT INTO Measurement (mid, measurementName) VALUES (2, 'Tablespoon');
INSERT INTO Measurement (mid, measurementName) VALUES (3, 'Ounce');
INSERT INTO Measurement (mid, measurementName) VALUES (4, 'Cup');
INSERT INTO Measurement (mid, measurementName) VALUES (5, 'Pint');
INSERT INTO Measurement (mid, measurementName) VALUES (6, 'Quart');
INSERT INTO Measurement (mid, measurementName) VALUES (7, 'Gallon');
INSERT INTO Measurement (mid, measurementName) VALUES (8, 'Fluid Ounce');
INSERT INTO Measurement (mid, measurementName) VALUES (9, 'Pound');
INSERT INTO Measurement (mid, measurementName) VALUES (10, 'Milliliter');
INSERT INTO Measurement (mid, measurementName) VALUES (11, 'Liter');
INSERT INTO Measurement (mid, measurementName) VALUES (12, 'Gram');
INSERT INTO Measurement (mid, measurementName) VALUES (13, 'Kilogram');
INSERT INTO Measurement (mid, measurementName) VALUES (14, 'Pinch');
INSERT INTO Measurement (mid, measurementName) VALUES (15, 'Clove');

-- Table: Recipe
CREATE TABLE IF NOT EXISTS Recipe (rid INTEGER PRIMARY KEY, recipeName TEXT, origin TEXT, favorite NUMERIC, image BLOB, num_cooked INTEGER, description TEXT, instructions TEXT, calories INTEGER, carbs INTEGER, fat INTEGER, protein INTEGER, servings INTEGER, prep_time TEXT, cook_time TEXT, total_time TEXT) WITHOUT ROWID;
INSERT INTO Recipe (rid, recipeName, origin, favorite, image, num_cooked, description, instructions, calories, carbs, fat, protein, servings, prep_time, cook_time, total_time) VALUES (1, 'Baked Ziti', 'Italy', 0, NULL, 0, 'Indulge in a comforting Italian classic with our baked ziti recipe. Al dente ziti pasta is tossed in a savory marinara sauce, layered with creamy ricotta cheese, and topped with melted mozzarella for a dish that''s both hearty and satisfying. Baked to golden perfection, this crowd-pleaser is perfect for family dinners or gatherings with friends.', 'Requires a 9x13 pan.\n1. Bring a large pot of lightly salted water to a boil. Add ziti pasta, and cook until al dente, about 8 minutes; drain.\n2. Meanwhile, brown ground beef and onion in a large skillet over medium heat; stir in spaghetti sauce and simmer for 15 minutes.\n3. Preheat the oven to 350 degrees F (175 degrees C). Butter the 9x13-inch baking dish.\n4. Spread one half of the ziti in the bottom of the prepared dish; top with Provolone cheese, sour cream, one half of the meat sauce, remaining ziti, mozzarella cheese, and remaining meat sauce. Top with grated Parmesan cheese.\n5. Bake in the preheated oven until heated through and cheeses have melted, about 30 minutes.', 578, 58, 25, 28, 10, '15 mins', '45 mins', '1 hr');
INSERT INTO Recipe (rid, recipeName, origin, favorite, image, num_cooked, description, instructions, calories, carbs, fat, protein, servings, prep_time, cook_time, total_time) VALUES (2, 'Oven Baked Chicken and Rice', 'Asia', 0, NULL, 0, 'Treat your taste buds to a delicious one-pan meal with our oven baked chicken and rice recipe. Succulent chicken pieces are seasoned to perfection and nestled atop a bed of fluffy rice infused with flavorful broth and aromatic herbs. As it bakes in the oven, the chicken becomes tender and juicy, while the rice absorbs all the savory goodness, creating a mouthwatering dish that''s both comforting and wholesome. Easy to prepare and requiring minimal cleanup, this recipe is perfect for busy weeknights or lazy weekends.', '1. Preheat oven to 180�C/350�F.\n2. Scatter onion and garlic in a baking dish (about 10 x 15" / 25 x 35 cm), then place butter in the centre. Bake for 15 minutes (check at 12 minutes, mix if some bits are browning too much).\n3. Meanwhile, mix together Chicken Rub. Sprinkle on both sides of the chicken.\n4. Remove baking dish from the oven. Add rice then mix.\n5. Place chicken on rice. Then pour chicken broth and water around the chicken.\n6. Cover with foil, then bake for 30 minutes. Remove foil, spray chicken with oil (optional, gives chicken nicer finish), then bake for a further 20 minutes until liquid is absorbed.\n7. Stand for 5 minutes, then remove chicken and fluff up rice. Garnish with parsley or thyme if desired, serve and enjoy!', 574, 47, 29, 28, 5, '10 mins', '1 hr 10 mins', '1 hr 20 mins');
INSERT INTO Recipe (rid, recipeName, origin, favorite, image, num_cooked, description, instructions, calories, carbs, fat, protein, servings, prep_time, cook_time, total_time) VALUES (3, 'Cheeseburgers', 'Germany', 0, NULL, 0, 'Sink your teeth into the ultimate comfort food with our classic cheeseburger recipe. Start with a juicy beef patty seasoned to perfection, grilled to perfection, and topped with creamy cheese. Bite into the perfect balance of savory, cheesy, and satisfying flavors that will have your taste buds singing. Whether you''re firing up the grill for a summer barbecue or craving a quick and delicious meal any time of year, our cheeseburger recipe is sure to be a crowd-pleaser for all ages.', '1. Mix the beef with the chopped onions.\n2. Add the seasoning to your preferred levels and mix well.\n3. Make 8 burger patties, making sure to keep them relatively even.\n4. Grill the patties at 400�F for ~7 minutes. Flip them, and grill for another 7 minutes.\n5. Flip the patties once again, top them with the cheese slices, and grill until cheese is melted partially.\n6. Tuck into a nice bun and add your favorite toppings.', 646, 3, 40, 36, 8, '30 mins', '20 mins', '50 mins');

-- Table: RecipeDishType
CREATE TABLE IF NOT EXISTS RecipeDishType (rid INTEGER REFERENCES Recipe (rid), dtid INTEGER REFERENCES DishType (dtid));
INSERT INTO RecipeDishType (rid, dtid) VALUES (1, 7);
INSERT INTO RecipeDishType (rid, dtid) VALUES (2, 5);
INSERT INTO RecipeDishType (rid, dtid) VALUES (3, 4);

-- Table: RecipeIngredient
CREATE TABLE IF NOT EXISTS RecipeIngredient (rid INTEGER REFERENCES Recipe (rid), iid INTEGER REFERENCES Ingredient (iid), mid INTEGER REFERENCES Measurement (mid), amount REAL, prepared TEXT);
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (1, 8, 2, 2.0, 'grated');
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (1, 7, 3, 6.0, 'shredded');
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (1, 6, 4, 1.5, NULL);
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (1, 5, 3, 6.0, 'sliced');
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (1, 4, 3, 52.0, NULL);
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (1, 3, 9, 1.0, NULL);
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (1, 2, NULL, 1.0, 'chopped');
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (1, 1, 9, 1.0, NULL);
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (2, 20, NULL, NULL, NULL);
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (2, 19, 1, 0.75, NULL);
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (2, 18, 1, 0.5, NULL);
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (2, 17, 1, 0.5, NULL);
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (2, 16, 1, 1.0, NULL);
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (2, 15, 1, 1.0, NULL);
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (2, 14, 4, 1.25, 'hot');
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (2, 13, 4, 1.5, NULL);
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (2, 12, 4, 1.5, NULL);
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (2, 11, 2, 2.0, NULL);
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (2, 10, 15, 2.0, 'minced');
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (2, 2, NULL, 1.0, 'chopped');
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (2, 9, NULL, 5.0, 'peel skin off');
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (3, 25, NULL, NULL, NULL);
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (3, 24, NULL, NULL, NULL);
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (3, 23, NULL, NULL, NULL);
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (3, 22, NULL, NULL, NULL);
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (3, 18, NULL, NULL, NULL);
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (3, 17, NULL, NULL, NULL);
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (3, 21, 3, 6.0, 'sliced');
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (3, 2, NULL, 0.25, 'chopped');
INSERT INTO RecipeIngredient (rid, iid, mid, amount, prepared) VALUES (3, 3, 9, 3.0, NULL);

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
