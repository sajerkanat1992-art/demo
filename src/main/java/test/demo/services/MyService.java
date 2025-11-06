package test.demo.services;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyService {

    public int findMin(String filePath, int n) throws IOException {
        List<Integer> numbers = readNumbersFromExcel(filePath);

        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("Файл не содержит чисел");
        }

        if (n < 1 || n > numbers.size()) {
            throw new IllegalArgumentException("N должно быть в диапазоне от 1 до " + numbers.size());
        }

        // ПРОСТОЙ АЛГОРИТМ - многопроходный поиск
        // Сложность: O(n * m) где m - количество чисел в файле
        return findNthMinMultiPass(numbers, n);
    }

    private int findNthMinMultiPass(List<Integer> numbers, int n) {
        // Создаем копию чтобы не менять исходный список
        List<Integer> numbersCopy = new ArrayList<>(numbers);

        // Удаляем n-1 минимальных элементов
        for (int i = 0; i < n - 1; i++) {
            int minIndex = findMinIndex(numbersCopy);
            numbersCopy.remove(minIndex);
        }

        // После удаления n-1 минимальных, следующий минимальный будет n-ным
        return numbersCopy.get(findMinIndex(numbersCopy));
    }
    private List<Integer> readNumbersFromExcel(String filePath) throws IOException {
        List<Integer> numbers = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            for (Row row : workbook.getSheetAt(0)) {
                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    numbers.add((int) cell.getNumericCellValue());
                }
            }
        }

        return numbers;
    }

    private int findMinIndex(List<Integer> numbers) {
        int minIndex = 0;
        int minValue = numbers.get(0);

        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) < minValue) {
                minValue = numbers.get(i);
                minIndex = i;
            }
        }
        return minIndex;
    }
}
