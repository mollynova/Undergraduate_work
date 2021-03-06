// do not change this file

#pragma warning(disable:4996)		// disable warning about strcopy being unsafe

#include "memoryleakdetect.h"		// this must be the first #include in each of your .cpp files
#include <cstring>
#include <iostream>
#include <iomanip>
#include "hashmap.h"

using namespace std;

HashMap		*hm;

static void printStocks(void)
{
	cout << endl
		 << "----------------------------------------------------------------------------"
		 << endl << endl
		 << *hm				// print the contents of the HashMap
		 << endl
		 << "----------------------------------------------------------------------------"
		 << endl << endl;
}

// The following 4 functions are used by main() to exercise your code.
// You can look at the details of them if you need to understand exactly
// how your code is being called.

// prints information about how an item was stored by your code
static void printAdditional(unsigned int symbolHash, unsigned int hashIndex,
							unsigned int usedIndex, unsigned int sequenceLgth)
{
	// keep printout of starter code from looking awful
	if (symbolHash == 0xCCCCCCCC && hashIndex == 0xCCCCCCCC &&
			usedIndex == 0xCCCCCCCC && sequenceLgth == 0xCCCCCCCC) {
		cout << endl;
		return;
		}

	cout << "(hc 0x"
		 << right
		 << hex << setfill('0')
		 << uppercase << setw(8) << symbolHash
		 << dec << setfill(' ')
		 << ", hi "		<< setw(2) << hashIndex
		 << ", ui "		<< setw(2) << static_cast<signed int>(usedIndex)
		 << ", sl "		<< setw(2) << sequenceLgth
		 << ")" 
		 << left << endl;
}

// exercises your HashMap::get() function
static void searchStock(char const * const symbol)
{
	unsigned int	usedIndex, hashIndex, symbolHash, sequenceLgth;
	Stock	s;

	if (hm->get(symbol, s, symbolHash, hashIndex, usedIndex, sequenceLgth))
		cout << "found " << left << setw(8) << symbol;
	else
		cout << symbol << " not found ";
	printAdditional(symbolHash, hashIndex, usedIndex, sequenceLgth);
}

// exercises your HashMap::put() function. Creates an instance of Stock, hands that
// to your code, and then totally destroys it. This makes sure that your code will
// fail unless you do a "deep copy" of the Stock instance.
static void addStock(char *symbol, char *name, int sharePrice, Date priceDate)
{
	unsigned int	usedIndex, hashIndex, symbolHash, sequenceLgth;
	char			*sym = new char[strlen(symbol) + 1];
	char			*nm =  new char[strlen(name)   + 1];
	Stock			*s;
	
	strcpy(sym, symbol);
	strcpy(nm, name);
	s = new Stock(sym, nm, sharePrice, priceDate);
	if (hm->put(*s, symbolHash, hashIndex, usedIndex, sequenceLgth)) {
		cout << "added " << left << setw(8) << s->getSymbol();
		printAdditional(symbolHash, hashIndex, usedIndex, sequenceLgth);
		}
	else
		cout << s->getSymbol() << " not added" << endl;

	delete[] sym;
	delete[] nm;
	delete s;
}

// exercises your HashMap::remove() function
static void removeStock(char *symbol)
{
	unsigned int	usedIndex, hashIndex, symbolHash, sequenceLgth;
	Stock	s;

	if (hm->remove(symbol, s, symbolHash, hashIndex, usedIndex, sequenceLgth)) {
		cout << "removed " << left << setw(6) << symbol;
		printAdditional(symbolHash, hashIndex, usedIndex, sequenceLgth);
		}
	else
		cout << symbol << " not removed" << endl;
}

int main(int argc, char **argv)
{
	cout << "CS260 - Assignment 3 - " << HashMap::YOUR_NAME << endl;

	hm = new HashMap(13);

	printStocks();
	searchStock("IBM");
	addStock("IBM", "International Business Machines", 2573, Date(Date::MAY, 23, 1967));
	printStocks();
	searchStock("IBM");
	removeStock("IBM");
	searchStock("IBM");
	removeStock("IBM");
	addStock("MLT", "MLT Software, Inc.", 800, Date(Date::APRIL, 18, 1988));
	addStock("IBM", "International Business Machines", 2573, Date(Date::MAY, 23, 1967));
	addStock("XRX", "Xerox", 1892, Date(Date::JUNE, 1, 1980));
	addStock("US:BA", "Boeing", 6407, Date(Date::DECEMBER, 14, 1993));
	addStock("GD", "General Dynamics", 7281, Date(Date::OCTOBER, 24, 2006));
	addStock("AAPL", "Apple Computer, Inc.", 2308, Date(Date::MAY, 1, 1980));
	addStock("AAPL", "Apple Computer, Inc.", 2308, Date(Date::MAY, 1, 1980));
	addStock("GE", "General Electric", 1948, Date(Date::MARCH, 22, 1987));
	addStock("ATT", "American Telephone & Telegraph", 20, Date(Date::MAY, 10, 1998));
	printStocks();
	searchStock("ATT");
	removeStock("AAPL");
	removeStock("AAPL");
	searchStock("ATT");
	removeStock("US:BA");
	searchStock("GE");
	removeStock("GE");
	searchStock("ATT");
	addStock("AAPL", "Apple Computer, Inc.", 2308, Date(Date::MAY, 1, 1980));
	addStock("GE", "General Electric", 1948, Date(Date::MARCH, 22, 1987));
	addStock("US:BA", "Boeing", 6407, Date(Date::DECEMBER, 14, 1993));
	printStocks();

	delete hm;
	
	// report on memory leaks in the Output Window
	#ifdef _DEBUG
	if (argc == 2) {
		_CrtSetReportMode( _CRT_WARN , _CRTDBG_MODE_FILE );
		_CrtSetReportFile( _CRT_WARN , _CRTDBG_FILE_STDERR );
		}
	_CrtDumpMemoryLeaks();
	#endif

	return 0;
}
