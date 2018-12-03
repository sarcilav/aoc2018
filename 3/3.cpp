using namespace std;
#include <algorithm>
#include <iostream>
#include <iterator>
#include <sstream>
#include <fstream>
#include <cassert>
#include <climits>
#include <cstdlib>
#include <cstring>
#include <string>
#include <cstdio>
#include <vector>
#include <cmath>
#include <queue>
#include <deque>
#include <stack>
#include <list>
#include <map>
#include <set>

template <class T> string toStr(const T &x)
{ stringstream s; s << x; return s.str(); }
template <class T> int toInt(const T &x)
{ stringstream s; s << x; int r; s >> r; return r; }

#define ALL(x) ((x).begin(),(x).end())
#define D(x) /*cout << #x " = " << (x) << endl */

const double EPS = 1e-9;
int cmp(double x, double y = 0, double tol = EPS){
    return( x <= y + tol) ? (x + tol < y) ? -1 : 0 : 1;
}

const int MAX = 1005;
vector< vector< vector< int> > > matrix(MAX, vector< vector<int> >(MAX));
set<int> ids;
int main(){
  int id, x, y, h, w;
  for(int i = 1; i <=1233; ++i) ids.insert(i);
  while(scanf("#%d @ %d,%d: %dx%d\n", &id, &x, &y, &w, &h) == 5)
    for(int j = y; j < y + h; ++j)
      for(int i = x; i < x + w; ++i)
        matrix[j][i].push_back(id);
  int ans = 0;
  for(int i = 0; i < MAX; ++i)
    for(int j = 0; j < MAX; ++j)
      if(matrix[i][j].size() > 1)
      {
        ++ans;
        for(int k = 0; k < matrix[i][j].size(); ++k)
          ids.erase(matrix[i][j][k]);
      }
  cout << ans << endl;
  cout << (*ids.begin()) << endl;
  cout << ids.size() ;
  return 0;
}
