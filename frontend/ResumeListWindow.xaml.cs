using System;
using System.Collections.Generic;
using System.Windows;
using Frontend.Models;
using Frontend.Services;

namespace Frontend
{
    public partial class ResumeListWindow : Window
    {
        private readonly ResumeService _resumeService;

        public ResumeListWindow()
        {
            InitializeComponent();
            _resumeService = new ResumeService();
            LoadResumesAsync();
        }

        private async void LoadResumesAsync()
        {
            try
            {
                List<Resume> resumes = await _resumeService.GetResumesAsync();
                ResumeListBox.Items.Clear();
                foreach (var resume in resumes)
                {
                    ResumeListBox.Items.Add(resume.ToString());
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Ошибка при загрузке резюме: {ex.Message}");
            }
        }
    }
}
