INSERT INTO dev_db.address(id, country, city, street, house, apartment) VALUES
	(1, 'Poland', 'Warsaw', 'Topolowa', 5, 45),
    (2, 'Poland', 'Krakow', 'Chelmonskiego', 9, 163),
    (3, 'United Kingdom', 'London', 'Easy Way', 35, 5),
    (4, 'Germany', 'Keln', 'Gereonswall', 95, 255),
    (5, 'USA', 'Boston', 'Grant St', 4, 8);    
INSERT INTO dev_db.clients(id, address_id, name, surname, age) VALUES
	(1, 1, 'Adam', 'Modzelewski', 15),
    (2, 2, 'Piotr', 'Nieczikowski', 68),
    (3, 3, 'Mike', 'Trenn', 35),
    (4, 4, 'Adrian', 'Kigler', 25),
    (5, 5, 'Anna', 'Faust', 33);
commit;
    