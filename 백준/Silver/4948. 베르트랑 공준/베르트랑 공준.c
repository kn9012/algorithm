#include<stdio.h>
#include<math.h>
int main()
{
	int n, i, j;
	int arr[246913] = { 1, 1 };

	for (i = 2; i < 246913; i++) {
		if (!arr[i]) {
			for (j = i + i; j < 246913; j += i)
				arr[j] = 1;
		}
	}

	while (1) {
		int count = 0;
		scanf("%d", &n);
		if (!n) { //n==0와 같은 뜻
			break;
		}
		else if (n == 1 || n == 2)
		{
			printf("1\n");
		}
		else {
			//에라토스테네스의 체 이용
			for (i = n + 1; i <= 2 * n; i++) {
				if (!arr[i])
					count++;
			}
			printf("%d\n", count);
		}
	}
}