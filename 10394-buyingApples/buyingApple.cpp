#include <iostream>
using namespace std;

int minimum(int n, int k, int cost[][101]) {
	int ret = 100 * 1000 + 1;
	for (int i = 0; i <= k; i++) {
		int tmp = cost[n - 1][i] + cost[1][k - i];
		if (tmp < ret)
			ret = tmp;
	}
	return ret;
}

int minCost(int n, int k, int* price) {
	int MaxCost = 100 * 1000 + 1;
	int cost[101][101] = { MaxCost };
	//init
	for (int i = 0; i < 101; i++)
	{
		for (int j = 0; j < 101; j++)
		{
			cost[i][j] = MaxCost;
		}
	}
	for (int i = 1; i <= k; ++i)
	{
		cost[1][i] = price[i - 1] == -1 ? MaxCost : price[i - 1];
	}
	cost[1][0] = 0;

	for (int i = 2; i <= n; ++i)
	{
		for (int j = 1; j <= k; ++j)
		{
			cost[i][j] = minimum(i, j, cost);
		}
	}

	if (cost[n][k] >= MaxCost)
		return -1;
	else
		return cost[n][k];
}

int main() {
	int num = 0;
	cin >> num;
	for (int tmp = 0; tmp < num; tmp++) {
		int n, k = 0;
		cin >> n >> k;
		int price[100];
		for (int i = 0; i < k; ++i)
		{
			cin >> price[i];
		}
		
		cout << minCost(n, k, price) << endl;
	}
	return 0;
}