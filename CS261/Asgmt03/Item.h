#pragma once

#include <ostream>
#include <vector>
#include <string>
#include<algorithm>

using namespace std;

class Item
{
private:
	const string title;
	const string artist;
	vector<string> keywords;
	int numStuff;

public:
	Item(const string title, const string artist, int numStuff);
	//Item();
	virtual ~Item();
	virtual const string getTitle() { return title; }
	virtual const string getArtist() { return artist; }
	virtual int getNumStuff() { return numStuff; }
	vector<string> getKeywords() { return keywords; }
	void addKeyword(string keyword) ;
	void sortKeywords();
};

// You can't store Item* in an ItemSet, because that would disable the automatic
// sorting that set does. Containers in the C++ STL are designed to store
// INSTANCES, not POINTERS to instances.
//
// Instead we store instances of ItemPtr in the ItemSet defined in Library.h.
// Each instance of ItemPtr contains a pointer to an instance of Item. In this way,
// the container can call operator< for ItemPtr (which can then call operator<
// for Item) to determine the order in which to store the items it's given.
//
// When you add a Book instance to an ItemSet, you can do things like this:
//
//		ItemSet		books;					// defined in Library.h
//		Item		*book = new Book(...);
//
//		books.insert(book);
//
// The STL's set class will internally generate an instance of ItemPtr
// which will contain the pointer to the instance of Book.

class ItemPtr
{
private:
	Item	*ptr;

public:
	ItemPtr(Item *ptr) : ptr(ptr) { }
	Item* getPtr() const { return ptr; }
};

// compare two instances of Item
bool operator<(Item i1, Item i2);

// compare two instances of ItemPtr
bool operator<(ItemPtr ip1, ItemPtr ip2);

ostream& operator<<(ostream& out, Item* item);




